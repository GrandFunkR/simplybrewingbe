/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.user.exception;

import org.spring.simplybrewing.exception.ApiException;

public class UserAlreadyExistsException extends ApiException {
    private static final long serialVersionUID = -2737319632275059973L;

    public UserAlreadyExistsException(String email) {
        super("User with email: " + email + " already registered");
    }
}
