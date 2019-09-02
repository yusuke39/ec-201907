package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class ShoppingCartService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	/**
	 * カートに商品を追加する.
	 * 
	 * @param order
	 */
	public void addItemToCart(Order order, OrderItem orderItem) {
		//自動採番で取ったOrderTableのID
		Order orderId = orderRepository.insert(order, orderItem);
		System.out.println(orderId);
	}
}
