package com.iquinto.userservice;

import com.iquinto.userservice.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceApplicationTests {
    @Autowired
    private  UserService userService;

    @Test
    void testLoad(){
        Assertions.assertNotNull(userService);
        assertEquals(userService.findAllAddress().size(), 8119);
        assertEquals(userService.findAllRoles().size(), 3);
        assertEquals(userService.findAllUsers().size(), 21);
    }
}
