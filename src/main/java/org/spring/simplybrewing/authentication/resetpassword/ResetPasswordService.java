/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.resetpassword;

import org.spring.simplybrewing.authentication.exception.PasswordsDontMatchException;
import org.spring.simplybrewing.user.ChangePasswordRequest;
import org.spring.simplybrewing.user.UserContextHolder;
import org.spring.simplybrewing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    private UserService userService;

    @Autowired
    public ResetPasswordService(UserService userService) {
        this.userService = userService;
    }

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (!resetPasswordDTO.getConfirmPassword().equals(resetPasswordDTO.getPassword())) {
            throw new PasswordsDontMatchException();
        }
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setUser(UserContextHolder.getUser());
        changePasswordRequest.setPassword(resetPasswordDTO.getPassword());
        userService.changePassword(changePasswordRequest);
    }

}
