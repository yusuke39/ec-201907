package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * ショッピングカートを操作するservice.
 * 
 * @author hiranoyuusuke
 *
 */
@Service
@Transactional
public class ShoppingCartService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	
	/**
	 * カートに商品を追加する.
	 * 
	 * @param order
	 */
	public void addItemToCart(Order order, OrderItem orderItem) {
		
		List<Order> orderList = orderRepository.findByStatusAndUserId(order.getStatus(), order.getUserId());
		
		if(orderList.size() == 0) {
			//オーダーレポシトリーにインサート
			order.setStatus(0);
			Order orderDomain = orderRepository.insert(order);
			orderItem.setOrderId(orderDomain.getId());
			
			//オーダーアイテムレポシトリーにインサート
			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);
			
			
			//オーダートッピングレポシトリーにインサート
			for(OrderTopping orderTopping : orderItem.getOrderToppingList() ) {
//				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setOrderItemId(orderItemDomain.getId());
//				orderTopping.setToppingId(toppings.getToppingId());
				orderToppingRepository.insert(orderTopping);
			}
			
			
		} else {
			//すでにショッピングカートが存在する場合
			
			//オーダーアイテムレポシトリーにインサート
			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);
			
			
			//オーダートッピングレポシトリーにインサート
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setOrderItemId(orderItemDomain.getId());
			orderToppingRepository.insert(orderTopping);
			
		}
	}
}
