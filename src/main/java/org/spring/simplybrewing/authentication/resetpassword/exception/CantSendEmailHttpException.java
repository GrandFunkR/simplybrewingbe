/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.resetpassword.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

public class CantSendEmailHttpException extends HttpException {
    public CantSendEmailHttpException() {
        super("Can't reset password, please, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
