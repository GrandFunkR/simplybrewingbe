/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication;

import org.spring.simplybrewing.authentication.exception.InvalidTokenHttpException;
import org.spring.simplybrewing.authentication.exception.UserAlreadyExistsHttpException;
import org.spring.simplybrewing.authentication.exception.UserNotFoundHttpException;
import org.spring.simplybrewing.entity.User;
import org.spring.simplybrewing.service.SettingsService;
import org.spring.simplybrewing.service.UserService;
import org.spring.simplybrewing.user.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private SettingsService settingsService;

    @Autowired
    public AuthService(UserService userService,
                       SettingsService settingsService,
                       AuthenticationManager authenticationManager,
                       TokenService tokenService) {
        this.settingsService = settingsService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public Token register(SignUpDTO signUpDTO) throws UserAlreadyExistsHttpException {
        try {
            User user = userService.register(signUpDTO);
            return createToken(user);
        } catch (UserAlreadyExistsException exception) {
            throw new UserAlreadyExistsHttpException();
        }
    }

    public Token login(LoginDTO loginDTO) throws UserNotFoundHttpException {
        try {
            Authentication authentication = createAuthentication(loginDTO);
            BundleUserDetailsService.BundleUserDetails userDetails =
                    (BundleUserDetailsService.BundleUserDetails) authenticationManager
                            .authenticate(authentication).getPrincipal();
            User user = userDetails.getUser();
            user.setSettings(settingsService.getSettingsByUserId(user.getId()));
            return createToken(user);
        } catch (AuthenticationException exception) {
            throw new UserNotFoundHttpException("Incorrect email or password", HttpStatus.FORBIDDEN);
        }
    }

    public Token refreshToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenHttpException {
        try {
            String email = tokenService.getEmailFromRefreshToken(refreshTokenDTO.getToken().getRefreshToken());
            User user = userService.findByEmail(email);
            return createToken(user);
        } catch (Exception e) {
            throw new InvalidTokenHttpException();
        }
    }

    private Authentication createAuthentication(LoginDTO loginDTO) {
        return new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
    }

    private Token createToken(User user) {
        return tokenService.createToken(user);
    }

}
