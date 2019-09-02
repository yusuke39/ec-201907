package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.repository.OrderHistoryRepository;
import com.example.service.showUserOrderHistoryService;

@Controller
@RequestMapping("/orderList")
public class ShowUserOrderHistoryController {
	
	@Autowired
	private showUserOrderHistoryService showUserOrderHistoryService;
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	@RequestMapping("")
	public String history() {
		return "order_history";
	}
	
	@RequestMapping("/orderHistoryList")
	public String loadUserOrderHistory(Integer userId) {
		List<Order> orderList = orderHistoryRepository.findAll();
		System.out.println(orderList);
		return "order_history";
	}
	

}
