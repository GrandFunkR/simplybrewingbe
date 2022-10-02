/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.authentication.resetpassword.exception;

import org.spring.simplybrewing.exception.HttpException;
import org.springframework.http.HttpStatus;

public class TokenNotFoundOrExpiredHttpException extends HttpException {
    public TokenNotFoundOrExpiredHttpException() {
        super("Reset password request wasn't performed or already expired", HttpStatus.FORBIDDEN);
    }
}
