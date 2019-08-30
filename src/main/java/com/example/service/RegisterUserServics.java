package com.example.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class RegisterUserServics {
	@Autowired
	private UserRepository userRepository;
	
	public void insert(User user) {
		userRepository.insert(user);
	}
}
