/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.repository;

import org.spring.simplybrewing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * The interface Role repository.
 */
@Repository
public
interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    Optional<Role> findByName(String name);
}
