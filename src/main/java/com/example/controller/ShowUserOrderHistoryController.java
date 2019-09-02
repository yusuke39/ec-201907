package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.showUserOrderHistoryService;

@Controller
@RequestMapping("/orderList")
public class ShowUserOrderHistoryController {
	
	@Autowired
	private showUserOrderHistoryService showUserOrderHistoryService;
	
	
	@RequestMapping("/orderHistoryList")
	public String loadUserOrderHistory(Integer userId) {
		
		
		return "orderHistory";
	}
	

}
