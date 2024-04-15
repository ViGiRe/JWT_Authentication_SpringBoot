package com.jwt.JWT_Authentication1_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JWT_Authentication1_Project.DTOs.SignUpRequest;
import com.jwt.JWT_Authentication1_Project.DTOs.UserDTO;
import com.jwt.JWT_Authentication1_Project.services.AuthService;

@RestController
public class SignUpUserController {

	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest){
		 UserDTO createUser = authService.createUser(signUpRequest);
		 if (createUser == null) {
			 return new ResponseEntity<>("User is not created...Try again", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);	
	}
	
	
}
