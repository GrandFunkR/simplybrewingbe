/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.resetpassword;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.simplybrewing.authentication.exception.PasswordsDontMatchException;
import org.spring.simplybrewing.authentication.resetpassword.exception.TokenNotFoundOrExpiredHttpException;
import org.spring.simplybrewing.user.ChangePasswordRequest;
import org.spring.simplybrewing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Service
public class RestorePasswordService {

    private RestorePasswordTokenRepository restorePasswordTokenRepository;
    private UserService userService;

    @Autowired
    public RestorePasswordService(RestorePasswordTokenRepository restorePasswordTokenRepository,
                                  UserService userService) {
        this.restorePasswordTokenRepository = restorePasswordTokenRepository;
        this.userService = userService;
    }

   /* public void restorePassword(RestorePasswordDTO restorePasswordDTO) {
        RestorePassword restorePassword =
                restorePasswordTokenRepository.findByToken(restorePasswordDTO.getToken());

        if (Objects.isNull(restorePassword) || restorePassword.isExpired()) {
            throw new TokenNotFoundOrExpiredHttpException();
        }

        changePassword(restorePasswordDTO, restorePassword);
        restorePasswordTokenRepository.delete(restorePassword);
    }*/

    public void restorePassword(RestorePasswordDTO restorePasswordDTO) throws IOException {
        if (!restorePasswordDTO.getNewPassword().equals(restorePasswordDTO.getConfirmPassword())) {
            throw new PasswordsDontMatchException();}
        byte[] decodedBytes = Base64.getDecoder().decode(restorePasswordDTO.getToken());
        String decodedToken = new String(decodedBytes);
        System.out.println("decoded Token" + decodedToken + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        RestorePasswordTokenDto restorePasswordTokenDto = objectMapper.readValue(decodedToken , RestorePasswordTokenDto.class);
        RestorePassword restorePassword = restorePasswordTokenRepository.findByToken(restorePasswordTokenDto.getToken());
        System.out.println("restore Password" + restorePassword);
        if (Objects.isNull(restorePassword) || restorePassword.isExpired()) {
            throw new TokenNotFoundOrExpiredHttpException();
        }
        changePassword(restorePasswordDTO, restorePassword);
        restorePasswordTokenRepository.delete(restorePassword);
    }

    public void removeExpiredRestorePasswordTokens() {
        restorePasswordTokenRepository.deleteExpiredRestorePasswordTokens(LocalDateTime.now());
    }

    private void changePassword(RestorePasswordDTO restorePasswordDTO,
                                RestorePassword restorePassword) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setUser(restorePassword.getUser());
        changePasswordRequest.setPassword(restorePasswordDTO.getNewPassword());
        userService.changePassword(changePasswordRequest);
    }
}
