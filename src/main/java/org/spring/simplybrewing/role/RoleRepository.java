/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.role;

import org.spring.simplybrewing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.isDefault = 1")
    Role findDefault();
}
