package org.spring.simplybrewing.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.spring.simplybrewing.dto.AddressDto;
import org.spring.simplybrewing.dto.UserDto;
import org.spring.simplybrewing.entity.Role;
import org.spring.simplybrewing.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type User mapper.
 *
 * @author Dario Iannaccone
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToRolesString")
    UserDto configUserToUserDTO(User user);


    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesStringToRoles")
    User configUserDTOToUser(UserDto userDto);


    @Named("rolesToRolesString")
    default Set<String> rolesToRolesString(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Named("rolesStringToRoles")
    default Set<Role> rolesStringToRoles(Set<String> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toSet());
    }

}
