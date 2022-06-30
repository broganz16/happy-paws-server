package com.iquinto.userservice.services;

import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService{

    // ROLE
    List<Role> findAllRoles();

    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByName(ERole eRole);

    void saveRole(Role role);

    // USER
    void saveUser(User user);

    boolean existsUserByUsername (String name);

    boolean existsUserByEmail (String email);
}
