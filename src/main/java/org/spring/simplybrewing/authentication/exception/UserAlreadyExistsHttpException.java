/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * The type User already exists http exception.
 */
public class UserAlreadyExistsHttpException extends HttpException {
    private static final long serialVersionUID = -5202433948475658078L;

    /**
     * Instantiates a new User already exists http exception.
     */
    public UserAlreadyExistsHttpException() {
        super("Email is invalid or already taken", HttpStatus.CONFLICT);
    }
}
