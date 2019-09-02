package com.example.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("/shopingCart")
public class ShoppingCartController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@ModelAttribute
	public ShoppingCartForm setShoppingCartForm() {
		return new ShoppingCartForm();
	}

	
	/**
	 * ショッピングカートに商品を加える.
	 * 
	 * @param form
	 * @return　ショッピングカートの中身
	 */
	@RequestMapping("/addItem")
	public String addItemToCart(ShoppingCartForm form) {
		
		Order order = new Order();
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		orderItem.setOrderToppingList(form.getToppingList());
		
		shoppingCartService.addItemToCart(order,orderItem);
		
		return "cart_list";
	}
	
}
