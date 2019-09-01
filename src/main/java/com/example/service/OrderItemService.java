package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;

import com.example.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	

	
	public Order load(Integer id) {
       Order order =  orderItemRepository.load(id);
       return order;
	}
	
	public void update(Order order) {
		orderItemRepository.update(order);
	}

}
