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
		System.out.println(orderItem.getOrderToppingList());
		
		order.setStatus(0);
		List<Order> orderList = orderRepository.findByStatusAndUserId(order.getStatus(), order.getUserId());
		
		
		if(orderList.size() == 0) {
			//オーダーレポシトリーにインサート
			Order orderDomain = orderRepository.insert(order);
			orderItem.setOrderId(orderDomain.getId());
			
			
			//オーダーアイテムレポシトリーにインサート
			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);
			
			
			//オーダートッピングレポシトリーにインサート
			for(OrderTopping orderTopping : orderItem.getOrderToppingList() ) {
				orderTopping.setOrderItemId(orderItemDomain.getId());
				orderToppingRepository.insert(orderTopping);
			}
			
			
		} else {
			//すでにショッピングカートが存在する場合
			
			//オーダーアイテムレポシトリーにインサート
			Order orderDomain = orderList.get(0);
			
			orderItem.setOrderId(orderDomain.getId());
			OrderItem orderItemDomain = orderItemRepository.insert(orderItem);
			
			
			//オーダートッピングレポシトリーにインサート（2回目以降の場合）
			//できれば下のFOR文はメソッド化してみたい
			for(OrderTopping orderToppingUpdate : orderItem.getOrderToppingList() ) {
				orderToppingUpdate.setOrderItemId(orderItemDomain.getId());
				orderToppingRepository.insert(orderToppingUpdate);
			}
		}
	}
	
	
	/**
	 * カートに入れた中身を検索する.
	 * 
	 * @param orderId
	 * @return
	 */
	public Order findShoppingCart(Integer orderId){
		Order orderItems = orderRepository.deepLoad(orderId);
		return orderItems;
	}
	
}
