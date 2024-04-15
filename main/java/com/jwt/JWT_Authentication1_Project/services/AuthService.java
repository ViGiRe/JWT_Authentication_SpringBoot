package com.jwt.JWT_Authentication1_Project.services;

import com.jwt.JWT_Authentication1_Project.DTOs.SignUpRequest;
import com.jwt.JWT_Authentication1_Project.DTOs.UserDTO;

public interface AuthService {

	UserDTO createUser(SignUpRequest signUpRequest);



}
