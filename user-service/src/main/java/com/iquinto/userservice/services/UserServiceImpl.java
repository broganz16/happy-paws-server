package com.iquinto.userservice.services;

import com.iquinto.userservice.models.Address;
import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.models.User;
import com.iquinto.userservice.repository.AddressRepository;
import com.iquinto.userservice.repository.RoleRepository;
import com.iquinto.userservice.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;


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

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    // ADDRESS
    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address>findAddressById(Long id) {
        return addressRepository.findById(id);
    }


    // TEST DATA
    @Override
    public void loadData(){
        try{
            Reader reader = Files.newBufferedReader(Paths.get(
                    ClassLoader.getSystemResource("data.csv").toURI()));

            CSVReader CSVReader =
                    new CSVReaderBuilder(reader).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();
            List<Address> csv_objectList=CSVReader.readAll().stream().map(data-> {
                Address csvObject= new Address();
                csvObject.setCity(data[1]);
                csvObject.setPostalCode(data[2]);
                csvObject.setProvince(data[3]);
                return csvObject;
            }).collect(Collectors.toList());
            addressRepository.saveAll(csv_objectList);
            System.out.println("ADDRESS IS LOADED " + addressRepository.findAll().size());



            Role admin = new Role();
            admin.setName(ERole.ROLE_ADMIN);
            roleRepository.save(admin);

            Role user = new Role();
            user.setName(ERole.ROLE_USER);
            roleRepository.save(user);

            Role moderator = new Role();
            moderator.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(moderator);

            System.out.println("ADDRESS IS LOADED " + roleRepository.findAll().size());

        } catch (IOException | CsvException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
