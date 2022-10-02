/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * The type Invalid token http exception.
 */
public class InvalidTokenHttpException extends HttpException {
    private static final long serialVersionUID = 773684525186809237L;

    /**
     * Instantiates a new Invalid token http exception.
     */
    public InvalidTokenHttpException() {
        super(HttpStatus.FORBIDDEN);
    }
}
