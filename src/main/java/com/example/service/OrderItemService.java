package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.OrderItemRepository;
import com.example.repository.OrderToppingRepository;


/**
 * ショッピングカート内を操作するサービスクラス.
 * @author hirokiokazaki
 *
 */
@Service
@Transactional
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	
	
	/**
	 * ショッピングカート内にある商品を削除.
	 * order_itemsテーブル(id)とorder_toppingsテーブル(orderId)は
	 * 結合している為同じ数値となる。
	 * @param id
	 */
	public void delete(Integer id) {
		orderItemRepository.deleteByOrderItemId(id);
		orderToppingRepository.deleteByOrderItemId(id);	
	}
	
	
}
