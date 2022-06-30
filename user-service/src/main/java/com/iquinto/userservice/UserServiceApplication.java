package com.iquinto.userservice;

import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.repository.RoleRepository;
import com.iquinto.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
/*
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
*/
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
/*@EnableDiscoveryClient*/
public class UserServiceApplication {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${server.port}")
	private int port;

	@Autowired
	UserService userService ;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		Role admin = new Role();
		admin.setName(ERole.ROLE_ADMIN);
		userService.saveRole(admin);

		Role user = new Role();
		user.setName(ERole.ROLE_USER);
		userService.saveRole(user);

		Role moderator = new Role();
		moderator.setName(ERole.ROLE_MODERATOR);
		userService.saveRole(moderator);
		System.out.println("Roles are loaded :  " + userService.findAllRoles().size());
		System.out.println("user service  is running at port:" + port);
	}
}
