package com.example.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class RegisterUserServics {
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * @param user
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}
	
	
	/**
	 * @param email
	 * @return
	 */
	public User findByMailAddress(String email) {
		return userRepository.findByMailAddress(email);
	}
}
