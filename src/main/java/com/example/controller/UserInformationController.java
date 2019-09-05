package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.UserInformationService;


@Controller
@RequestMapping("/userInformation")
public class UserInformationController {
	
	@Autowired
	public UserInformationService userInformationService;
	
	@RequestMapping("/showList")
	public String showlist(Model model) {
		List<User> userInformationList = userInformationService.showList();
		model.addAttribute("userList", userInformationList);
		return "userInformation/administratorList";
	}
}
