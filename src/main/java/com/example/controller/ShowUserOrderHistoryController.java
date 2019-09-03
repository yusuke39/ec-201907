package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.repository.OrderHistoryRepository;
import com.example.service.showUserOrderHistoryService;

@Controller
@RequestMapping("/orderHistory")
public class ShowUserOrderHistoryController {
	
	@Autowired
	private showUserOrderHistoryService showUserOrderHistoryService;
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@RequestMapping("/list")
	public String loadUserOrderHistory(Integer userId,Model model) {
		List<Order> orderHistoryList = orderHistoryRepository.findAll();
		System.out.println(orderHistoryList);
		
		model.addAttribute("orderHistoryList", orderHistoryList);
		return "order_history";
	}
	

}
