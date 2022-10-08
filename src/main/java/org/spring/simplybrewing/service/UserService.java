/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing.service;

import org.spring.simplybrewing.authentication.SignUpDTO;
import org.spring.simplybrewing.authentication.exception.PasswordsDontMatchException;
import org.spring.simplybrewing.authentication.exception.UserNotFoundHttpException;
import org.spring.simplybrewing.dto.SettingsDto;
import org.spring.simplybrewing.dto.UserDto;
import org.spring.simplybrewing.entity.Settings;
import org.spring.simplybrewing.entity.User;
import org.spring.simplybrewing.mapper.UserMapper;
import org.spring.simplybrewing.repository.UserRepository;
import org.spring.simplybrewing.user.ChangePasswordRequest;
import org.spring.simplybrewing.user.UserContextHolder;
import org.spring.simplybrewing.user.exception.UserAlreadyExistsException;
import org.spring.simplybrewing.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    private SettingsService settingsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       SettingsService settingsService,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.settingsService = settingsService;
        this.roleService = roleService;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));
    }

    @Transactional
    public User register(SignUpDTO signUpDTO) throws UserAlreadyExistsException {
        if (!signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
            throw new PasswordsDontMatchException();
        }

        String email = signUpDTO.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException(email);
        }

        User user = signUpUser(signUpDTO);

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = changePasswordRequest.getUser();

        String encodedPassword = encodePassword(changePasswordRequest.getPassword());
        user.setPasswordHash(encodedPassword);

        userRepository.save(user);
    }

    public User getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundHttpException("User with id: " + id + " not found", HttpStatus.NOT_FOUND)
        );

        return existingUser;
    }

    public UserDto getUserDtoById(Long id) {
        User existingUser = getUserById(id);
        return userMapper.configUserToUserDTO(existingUser);
    }

    @Transactional
    public UserDto updateUserById(Long userId, UserDto userDTO) {
        return updateUser(userId, userDTO);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundHttpException("User with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public UserDto getCurrentUser() {
        User user = UserContextHolder.getUser();
        user.setSettings(settingsService.getSettingsByUserId(user.getId()));

        return userMapper.configUserToUserDTO(user);
    }

    public UserDto updateUserSettings(SettingsDto settingsDTO) {
        User user = UserContextHolder.getUser();

        Settings settings = user.getSettings();
        settings.setThemeName(settingsDTO.getThemeName());


        /*Settings settings = modelMapper.map(settingsDTO, Settings.class);
        user.setSettings(settingsService.findByThemeName(settings.getThemeName()));*/


        userRepository.save(user);
        return userMapper.configUserToUserDTO(user);
    }

    public UserDto updateCurrentUser(UserDto userDTO) {
        User user = UserContextHolder.getUser();
        Long id = user.getId();

        return updateUser(id, userDTO);
    }

    @Transactional
    public UserDto createUser(UserDto userDTO) {
        User user = userMapper.configUserDTOToUser(userDTO);

        // In current version password and role are default
        user.setPasswordHash(encodePassword("testPass"));
        user.setRoles(new HashSet<>(Collections.singletonList(roleService.getDefaultRole())));
        userRepository.save(user);
        return userMapper.configUserToUserDTO(user);
    }

    private UserDto updateUser(Long id, UserDto userDTO) {
        User existingUser = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundHttpException(
                        "User with id: " + id + " not found", HttpStatus.NOT_FOUND)
                );

        User updatedUser = userMapper.configUserDTOToUser(userDTO);
        updatedUser.setId(id);
        updatedUser.setPasswordHash(existingUser.getPasswordHash());
        // Current version doesn't update roles
        updatedUser.setRoles(existingUser.getRoles());

        userRepository.save(updatedUser);

        return userMapper.configUserToUserDTO(updatedUser);
    }

    private User signUpUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setEmail(signUpDTO.getEmail());
        user.setUserName(signUpDTO.getFullName());

        String encodedPassword = encodePassword(signUpDTO.getPassword());
        user.setPasswordHash(encodedPassword);
        user.setRoles(new HashSet<>(Collections.singletonList(roleService.getDefaultRole())));
        user.setSettings(Settings.builder().themeName("default").build());
        return user;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
