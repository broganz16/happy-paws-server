package com.iquinto.userservice.services;

import com.iquinto.userservice.models.Address;
import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.models.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    Optional<User> findUserByUsername(String username);

    List<Address> findAllAddresses();

    void loadData();

}
