package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String addItemToCart(ShoppingCartForm form, Model model) {
		
		//ショッピングカートに追加する
		Order order = new Order();
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		//仮のユーザーID取得
		order.setUserId(Integer.parseInt(form.getUserId()));
	
		OrderItem orderItem = new OrderItem();
		char[] size = form.getSize().toCharArray();
		orderItem.setItemId(Integer.parseInt(form.getItemId()));
		orderItem.setSize(size[0]);
		orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		List<OrderTopping> orderToppingList = new ArrayList<>();
		OrderTopping orderTopping = new OrderTopping();
		
		if(form.getToppingList() == null) {
			orderToppingList.add(orderTopping);
		}
		for(Integer toppingId : form.getToppingList()) {
			orderTopping.setToppingId(toppingId);
			orderToppingList.add(orderTopping);
		}
		
		orderItem.setOrderToppingList(orderToppingList);
		
		shoppingCartService.addItemToCart(order,orderItem);
		
		return "redirect:/showCart";
	}
	
	@RequestMapping("/showCart")
	public String showCart(Model model) {
		int status = 0;
//		int user_id = loginuser.getUser().getId();
		List<Order> orderItemList = orderRepository.findByStatusAndUserId(status, 1);
		
		if(orderItemList.size() == 0) {
			model.addAttribute("CartIsNull", "ショッピングカートは空です");
		} else {
			Order order = orderItemList.get(0);
			Order orderItems = shoppingCartService.findShoppingCart(order.getId());
			model.addAttribute("orderItems", orderItems);
			System.out.print(orderItems);
		}
		
		return "cart_list";
	}
	
	
	
}
