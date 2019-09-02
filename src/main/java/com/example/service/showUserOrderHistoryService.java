package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderHistoryRepository;

@Service
@Transactional
public class showUserOrderHistoryService{

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	public List<Order> loadUserOrderHistory(Integer userId) {
		return orderHistoryRepository.findByUserId(userId);
	}
}
