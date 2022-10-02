/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.spring.simplybrewing.entity.Settings;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type User dto.
 */
@Data
@NoArgsConstructor
public class UserDto {

    /**
     * The User name.
     */
    @NotEmpty
    @NotNull
    public String userName;

    /**
     * The Email.
     */
    @NotEmpty
    @NotNull
    public String email;

    /**
     * The First name.
     */
    public String firstName;
    /**
     * The Last name.
     */
    public String lastName;
    /**
     * The Age.
     */
    public Integer age;
    /**
     * The Address.
     */
    public AddressDto address;
    /**
     * The Roles.
     */
    public Set<String> roles;

    /**
     * The Settings.
     */
    public Settings settings;

    /**
     * Instantiates a new User dto.
     *
     * @param userName the user name
     * @param email    the email
     */
    public UserDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
