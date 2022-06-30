package com.iquinto.userservice.services;

import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.models.User;
import com.iquinto.userservice.repository.RoleRepository;
import com.iquinto.userservice.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findRoleByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsUserByUsername(String name) {
        return userRepository.existsByUsername(name);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
