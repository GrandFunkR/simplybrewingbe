/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * The type User not found http exception.
 */
public class UserNotFoundHttpException extends HttpException {
    private static final long serialVersionUID = 4770986620665158856L;

    /**
     * Instantiates a new User not found http exception.
     *
     * @param message the message
     * @param status  the status
     */
    public UserNotFoundHttpException(String message, HttpStatus status) {
        super(message, status);
    }

}
