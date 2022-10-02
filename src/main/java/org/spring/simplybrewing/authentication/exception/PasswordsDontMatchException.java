/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * The type Passwords dont match exception.
 */
public class PasswordsDontMatchException extends HttpException {

    private static final long serialVersionUID = -7852550573176915476L;

    /**
     * Instantiates a new Passwords dont match exception.
     */
    public PasswordsDontMatchException() {
        super("Passwords don't match", HttpStatus.BAD_REQUEST);
    }
}
