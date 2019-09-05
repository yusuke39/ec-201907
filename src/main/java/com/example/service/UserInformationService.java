package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserInformationRepository;


@Service
public class UserInformationService {

	@Autowired
	private UserInformationRepository userInformationRepository;
	
	
	public List<User> showList() {
		List<User> userInformationList = userInformationRepository.findAll();
		return userInformationList;
	}
	
	public User showDetail(Integer id) {
		User User = userInformationRepository.load(id);
		return User;
	}
	
}
