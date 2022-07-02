package com.iquinto.userservice.controllers;

import com.iquinto.userservice.models.User;
import com.iquinto.userservice.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserService userService;


	@GetMapping("/monitor")
	public String monitor() {
		log.info("[m:monitor] USER-SERVICE IS WORKING ");
		return  "USER-SERVICE IS WORKING";
	}
	
	@GetMapping("/all")
	public List<User> allAccess() {
		log.info("[m:monitor] USER-SERVICE IS WORKING ");
		return  userService.findAllUsers();
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
