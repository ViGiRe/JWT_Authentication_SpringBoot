package com.jwt.JWT_Authentication1_Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.JWT_Authentication1_Project.DTOs.SignUpRequest;
import com.jwt.JWT_Authentication1_Project.DTOs.UserDTO;
import com.jwt.JWT_Authentication1_Project.Entity.User;
import com.jwt.JWT_Authentication1_Project.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(SignUpRequest signUpRequest) {
		User user = new User();
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setPhone(signUpRequest.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
		User userCreated = userRepository.save(user);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setName(userCreated.getName());
		userDTO.setEmail(userCreated.getEmail());
		userDTO.setPhone(userCreated.getPhone());
		return userDTO;
	}


	
}
