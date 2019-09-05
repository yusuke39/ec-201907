package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.OrderService;
import com.example.service.UserDetailsServiceImpl;
import com.example.service.UserInformationService;


/**
 * @author junya.yoshioka
 *
 */
@Controller
@RequestMapping("/userInformation")
public class UserInformationController {
	
	@Autowired
	public UserInformationService userInformationService;
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	
	
	@RequestMapping("/showList")
	public String showlist(Model model) {
		List<User> userInformationList = userInformationService.showList();
		model.addAttribute("userList", userInformationList);
		return "userInformation/administratorList";
	}
	
	
	
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		User user = userInformationService.showDetail(Integer.parseInt(id));
		model.addAttribute("user", user);
		
		List<Order> orderHistoryList = orderService.load();
		model.addAttribute("orderHistoryList", orderHistoryList);
		return "userInformation/administratorDetail";
	}
	
	@RequestMapping("delete")
	public String delete(Integer id,Model model){
		userDetailsServiceImpl.delete(id);
		return "redirect:/userInformation/showList";
	}
	
	
}
