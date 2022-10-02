/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.spring.simplybrewing.entity.Role;
import org.spring.simplybrewing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class TokenService {
    @Value("${jwt.accessTokenSecretKey}")
    private String accessTokenSecretKey;

    @Value("${jwt.refreshTokenSecretKey}")
    private String refreshTokenSecretKey;

    @Value("${jwt.accessTokenValidityInMilliseconds}")
    private long accessTokenValidityInMilliseconds;

    @Value("${jwt.refreshTokenValidityInMilliseconds}")
    private long refreshTokenValidityInMilliseconds;

    private UserDetailsService userDetailsService;

    @Autowired
    public TokenService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        accessTokenSecretKey = Base64.getEncoder().encodeToString(accessTokenSecretKey.getBytes(UTF_8));
    }

    Token createToken(User user) {
        Token token = new Token();
        long expiresIn = expiration(accessTokenValidityInMilliseconds);

        token.setAccessToken(createAccessToken(user));
        token.setRefreshToken(createRefreshToken(user));
        token.setExpiresIn(expiresIn);

        return token;
    }

    Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmailFromAccessToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodeToken(String secret, String token) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }


    String getEmailFromAccessToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJwt = decodeToken(accessTokenSecretKey, token);
        return decodedJwt.getSubject();
    }

    String getEmailFromRefreshToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJwt = decodeToken(refreshTokenSecretKey, token);
        return decodedJwt.getSubject();
    }

    String resolveToken(HttpServletRequest req) {
        String bearer = "Bearer ";
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(bearer)) {
            return bearerToken.substring(bearer.length());
        }
        return null;
    }

    boolean isValid(String token) throws Exception {
        try {
            DecodedJWT decodedJwt = decodeToken(accessTokenSecretKey, token);
            return !decodedJwt.getExpiresAt().before(new Date());
        } catch (Exception  e) {
            throw new Exception("Expired or invalid JWT token");
        }
    }

    private String createAccessToken(User user) {
        long expiresIn = expiration(accessTokenValidityInMilliseconds);

        return createToken(user, expiresIn, accessTokenSecretKey);
    }

    private String createRefreshToken(User user) {
        long expiresIn = expiration(refreshTokenValidityInMilliseconds);

        return createToken(user, expiresIn, refreshTokenSecretKey);
    }

    private List<String> getRoleNames(Set<Role> roles) {
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getName().toLowerCase());
        }
        return roleNames;
    }

    private String createToken(User user, long expiresIn, String key) {
        Date now = new Date();
        Date expirationDate = new Date(expiresIn);

        Algorithm algorithm = Algorithm.HMAC512(key);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("fullName", String.join(" ", user.getUserName()))
                .withClaim("createdAt", user.getCreatedAt())
                .withClaim("role", getRoleNames(user.getRoles()).stream().collect(Collectors.joining(",")))
                .withClaim("themeName", user.getSettings().getThemeName())
                .withExpiresAt(expirationDate)
                .withSubject(user.getEmail())
                .sign(algorithm);

        return token;
    }

    private long expiration(long validity) {
        Date now = new Date();
        return now.getTime() + validity;
    }
}
