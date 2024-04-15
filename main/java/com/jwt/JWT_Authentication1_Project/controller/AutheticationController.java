package com.jwt.JWT_Authentication1_Project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JWT_Authentication1_Project.DTOs.AuthenticationRequest;
import com.jwt.JWT_Authentication1_Project.DTOs.AuthenticationResponse;
import com.jwt.JWT_Authentication1_Project.services.JWT.UserDetailServiceImplementation;
import com.jwt.JWT_Authentication1_Project.utils.JWTUtils;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AutheticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImplementation userDetailServiceImplementation;
	
	@Autowired
	private JWTUtils jwtUtil;
	
	
	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException,DisabledException,UsernameNotFoundException,IOException{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect UserName or Password");
		} catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "USER is not created...Register First");
			return null;
		}
		final UserDetails userDetails = userDetailServiceImplementation.loadUserByUsername(authenticationRequest.getEmail()) ;
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		return new AuthenticationResponse(jwt);
		}
	
	

}
