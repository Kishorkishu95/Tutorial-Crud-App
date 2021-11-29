package com.kishor.tutrialapp.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.tutrialapp.Dto.JwtAuthResponseDto;
import com.kishor.tutrialapp.Dto.LoginDto;
import com.kishor.tutrialapp.Dto.SignupDto;
import com.kishor.tutrialapp.entity.Role;
import com.kishor.tutrialapp.entity.User;
import com.kishor.tutrialapp.repository.RoleRepository;
import com.kishor.tutrialapp.repository.UserRepository;
import com.kishor.tutrialapp.security.JwtTokenProvider;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto){
		Authentication authentication= authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// get token from tokenProvider 
		String token = jwtTokenProvider.genarateToken(authentication);
		return ResponseEntity.ok(new JwtAuthResponseDto(token));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignupDto signupDto){
		// check whether username or email exists in database
		if(userRepository.existsUserByUsername(signupDto.getUsername())) {
			return new ResponseEntity<String>("Username is already used.",HttpStatus.BAD_REQUEST);
		}
		if(userRepository.existsUserByEmail(signupDto.getEmail())) {
			return new ResponseEntity<String>("Email is already used.",HttpStatus.BAD_REQUEST);
		}
		// Set user from signupdto
		User user = new User();
		user.setName(signupDto.getName());
		user.setEmail(signupDto.getEmail());
		user.setUsername(signupDto.getUsername());
		user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		
		Role userRole = roleRepository.findByName("ADMIN").get();
		user.setRoles(Collections.singleton(userRole));
		userRepository.save(user);
		return new ResponseEntity<String>("User registered successfully.",HttpStatus.OK);
	}
}
