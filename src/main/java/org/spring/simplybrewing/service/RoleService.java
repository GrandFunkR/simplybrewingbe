/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.service;

import org.spring.simplybrewing.entity.Role;
import org.spring.simplybrewing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Role service.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    /**
     * Gets default role.
     *
     * @return the default role
     */
    public Role getDefaultRole() {
        return roleRepository.findByName("USER").orElseThrow();
    }

}
