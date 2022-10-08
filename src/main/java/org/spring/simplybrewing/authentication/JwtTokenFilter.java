/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JwtTokenFilter extends GenericFilterBean {
    private TokenService tokenService;


    JwtTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private static final List<String> AUTH_WHITELIST = Arrays.asList(
            "/api/auth/login",
            "/api/auth/restore-pass",
            "/api/auth/sign-up",
            "/api/auth/request-pass",
            "/api/auth/sign-out",
            "/api/auth/refresh-token");


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = tokenService.resolveToken((HttpServletRequest) req);

        try {
            String path = ((HttpServletRequest) req).getRequestURI();

            if (AUTH_WHITELIST.contains(path)) {
                filterChain.doFilter(req, res);
                return;
            }


            if (token == null || !tokenService.isValid(token)) {
                throw new BadCredentialsException("Invalid Token received!");
            }

            Authentication auth = tokenService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);


            filterChain.doFilter(req, res);

        } catch (BadCredentialsException e) {
            SecurityContextHolder.clearContext();
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(401);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(500);
        }


    }
}
