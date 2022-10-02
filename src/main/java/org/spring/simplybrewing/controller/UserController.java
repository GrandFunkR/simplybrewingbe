/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.controller;

import org.spring.simplybrewing.dto.SettingsDto;
import org.spring.simplybrewing.dto.UserDto;
import org.spring.simplybrewing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for managing users
 */
@Controller
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user. Allowed only for Admin
     *
     * @param id user id
     * @return user user
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        return ok(userService.getUserDtoById(id));
    }

    /**
     * Update user. Allowed only for Admin
     *
     * @param id      user id
     * @param userDTO updated user data
     * @return updated user data
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDTO) {
        UserDto updatedUser = userService.updateUserById(id, userDTO);
        return ok(updatedUser);
    }

    /**
     * Delete user
     *
     * @param id user id
     * @return boolean result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ok(userService.deleteUser(id));
    }

    /**
     * Get current user
     *
     * @return current user data
     */
    @GetMapping("/current")
    public ResponseEntity getCurrentUser() {
        return ok(userService.getCurrentUser());
    }

    /**
     * Update current user
     *
     * @param userDTO updated user data
     * @return updated user data
     */
    @PutMapping("/current")
    public ResponseEntity updateCurrentUser(@Valid @RequestBody UserDto userDTO) {
        UserDto updatedUser = userService.updateCurrentUser(userDTO);
        return ok(updatedUser);
    }

    /**
     * Create user. Allowed only for Admin
     *
     * @param userDTO new user data
     * @return created user
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDTO) {
        return ok(userService.createUser(userDTO));
    }

    /**
     * Update user settings response entity.
     *
     * @param settingsDTO the settings dto
     * @return the response entity
     */
    @PutMapping("/current/theme")
    public ResponseEntity updateUserSettings(@Valid @RequestBody SettingsDto settingsDTO) {
        System.out.println("reached");
        return ok(userService.updateUserSettings(settingsDTO));
    }
}
