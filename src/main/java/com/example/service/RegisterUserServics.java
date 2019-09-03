package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class RegisterUserServics {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * @param user
	 */
	public void insert(User user) {
		
//		パスワードをハッシュ化する
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
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
