package com.iquinto.userservice.controllers;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.iquinto.userservice.models.Address;
import com.iquinto.userservice.payload.request.UpdateRequest;
import com.iquinto.userservice.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import com.iquinto.userservice.models.User;
import com.iquinto.userservice.payload.request.LoginRequest;
import com.iquinto.userservice.payload.request.SignupRequest;
import com.iquinto.userservice.payload.response.JwtResponse;
import com.iquinto.userservice.payload.response.MessageResponse;
import com.iquinto.userservice.security.jwt.JwtUtils;
import com.iquinto.userservice.security.services.UserDetailsImpl;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		log.info("[c:AuthController  m:authenticateUser] starts : " + loginRequest);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		log.info("[c:AuthController  m:authenticateUser] is authenticated! ");

		User user = userService.findUserByUsername(userDetails.getUsername()).orElse(null);

		JwtResponse jwtResponse = new JwtResponse(jwt,
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				roles);
		jwtResponse.setName(user.getName());
		jwtResponse.setSurname(user.getSurname());
		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		log.info("[c:AuthController  m:registerUser] starts : " + signUpRequest);
		if (userService.existsUserByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsUserByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = userService.findRoleByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = userService.findRoleByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = userService.findRoleByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = userService.findRoleByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		/** ADDED **/
		user.setName(signUpRequest.getName());
		user.setSurname(signUpRequest.getSurname());
		user.setPhone(signUpRequest.getPhone());

		Address address = userService.findAddressById(signUpRequest.getAddressId()).get();
		user.setAddress(address);
		userService.saveUser(user);

		log.info("[c:AuthController  m:registerUser] user is created : " + user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@RequestMapping(value ="/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity update(@Valid UpdateRequest updateRequest) {
		log.info("[c:AuthController  m:update] starts " + updateRequest);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		log.info("[c:AuthController  m:update ]  current username" + currentUserName);

		User user = userService.findUserByUsername(currentUserName).orElse(null);
		assert user != null;

		if(!Objects.equals(updateRequest.getAddressId(), user.getAddress().getId())){
			Address address = userService.findAddressById(updateRequest.getAddressId()).orElse(null);
			if(address == null){
				log.warn("[c:AuthController  m:update ]  address not found! ");
			}else{
				user.setAddress(address);
			}
		}

		user.setName(updateRequest.getName());
		user.setSurname(updateRequest.getSurname());
		user.setPhone(updateRequest.getPhone());
		user.setDescription(updateRequest.getDesciption());

		return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
	}

	@RequestMapping(value ="/address", method = RequestMethod.GET)
	public ResponseEntity<?> address(@RequestParam String query) {
		log.info("[c: address] starts " + query);
		List<Address> list = userService.findAllByQuery(query.toLowerCase());
		log.info("[c: address] list " + list.size());
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@RequestMapping(value ="/check-token/{token}", method = RequestMethod.GET)
	public ResponseEntity<?> checkToken(@PathVariable String token) {
		log.info("[c: checkToken] starts " + token);
		boolean valid = jwtUtils.validateJwtToken(token);
		log.info("[c: checkToken] valid " + valid);

		String username = jwtUtils.getUserNameFromJwtToken(token);
		log.info("[c: checkToken] username " + username);

		Map<String, Object> map = new HashMap<>();
		map.put("valid", valid);
		map.put("username", username);

		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

}
