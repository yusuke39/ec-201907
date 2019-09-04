package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderRepository;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("/shopingCart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderRepository orderRepository;
	
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
	public String addItemToCart(ShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser ,Model model) {
		
		//ショッピングカートに追加する
		Order order = new Order();
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		//ユーザーID取得
		int user_id = loginUser.getUser().getId();
		order.setUserId(user_id);
	
		OrderItem orderItem = new OrderItem();
		char[] size = form.getSize().toCharArray();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setSize(size[0]);
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		List<OrderTopping> orderToppingList = new ArrayList<>();
		
		if(form.getToppingList() != null) {
		for(Integer toppingId : form.getToppingList()) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(toppingId);
			orderToppingList.add(orderTopping);
		}
}
		
		orderItem.setOrderToppingList(orderToppingList);
		
		shoppingCartService.addItemToCart(order,orderItem);
		
		return "redirect:/shopingCart/showCart";
	}
	
	@RequestMapping("/showCart")
	public String showCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		int status = 0;
		int user_id = loginUser.getUser().getId();
		List<Order> orderList = orderRepository.findByStatusAndUserId(status, user_id);
		
		if(orderList.size() == 0) {
			Order order = new Order();
			order.setOrderItemList(new ArrayList<OrderItem>());
			model.addAttribute("order", order);
		} else {
			Order order = orderList.get(0);
			model.addAttribute("order", order);
		}
		
		return "cart_list";
	}
	
	
	
}
