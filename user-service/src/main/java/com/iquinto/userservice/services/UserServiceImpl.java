package com.iquinto.userservice.services;

import com.github.javafaker.Faker;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final  AddressRepository addressRepository;

    private final PasswordEncoder encoder;

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
    public User saveUser(User user) {
        return userRepository.save(user);
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

    @Override
    public List<Address> findAllByQuery(String query){
        return addressRepository.findAllByQuery(query);
    }



    // USER
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    // TEST DATA
    @Override
    public void loadData(){


        Role admin = new Role();
        admin.setName(ERole.ROLE_ADMIN);
        roleRepository.save(admin);

        Role user = new Role();
        user.setName(ERole.ROLE_USER);
        roleRepository.save(user);

        Role moderator = new Role();
        moderator.setName(ERole.ROLE_MODERATOR);
        roleRepository.save(moderator);
        System.out.println("ROLES IS LOADED : " + this.findAllRoles().size());


        try (InputStream inputStream = getClass().getResourceAsStream("/address.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            CSVReader CSVReader =
                    new CSVReaderBuilder(reader).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();
            List<Address> csv_objectList=CSVReader.readAll().stream().map(data-> {
                Address csvObject= new Address(data[1], data[2], data[3]);
                return csvObject;
            }).collect(Collectors.toList());
            addressRepository.saveAll(csv_objectList);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        System.out.println("ADDRESS IS ADDED : " + this.findAllAddress().size());

        User u = new User();
        u.setName("Giovana");
        u.setSurname("Diana");
        u.setEmail("test@gmail.com");
        u.setPhone("600075568");
        u.setUsername("gdiana");
        u.setPassword(encoder.encode("whatthefuck"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
        u.setRoles(roles);
        int randomId =  (int) (Math.random() * 50 + 1);
        long r = randomId;
        u.setAddress(addressRepository.findById(2L).get());
        userRepository.save(u);

        int i;
        for (i = 0; i < 20; i++){
            Faker faker = new Faker();
            User user1 = new User();
            user1.setName(faker.name().firstName());
            user1.setSurname(faker.name().lastName());
            user1.setEmail(faker.name().username() + "@gmail.com");
            user1.setPhone("600075568");
            user1.setUsername(faker.name().username() );
            user1.setPassword(encoder.encode("whatthefuck"));
            user1.setRoles(roles);
            int rIdI =  (int) (Math.random() * 50 + 1);
            long rId = rIdI;
            user1.setAddress(addressRepository.findById(rId).get());
            userRepository.save(user1);
        }

        System.out.println("USERS IS ADDED : " + this.findAllUsers().size());

    }
}
