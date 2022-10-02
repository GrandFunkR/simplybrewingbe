/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.user;

import org.spring.simplybrewing.authentication.BundleUserDetailsService;
import org.spring.simplybrewing.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextHolder {

    private UserContextHolder() {
    }

    public static User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BundleUserDetailsService.BundleUserDetails userDetails = (BundleUserDetailsService.BundleUserDetails) principal;
        return userDetails.getUser();
    }
}
