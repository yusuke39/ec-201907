package com.example.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderService;

@Controller
@RequestMapping("/checkOrder")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	@RequestMapping("confirm")
	public String confirm(@AuthenticationPrincipal LoginUser loginAdministrator) {
//		System.out.println(loginAdministrator.getUser().getName() + "さんがログイン中");
		return "cart_list";
	}
	
	@RequestMapping("decision")
	public String decision(Integer orderId, Model model) {
		Order order = orderService.showDetail(orderId);
		
//		Order order = new Order();
//		order.setId(3);
		model.addAttribute("order", order);
		return "order_confirm";
	}
	
	@RequestMapping("update")
	public String order(OrderForm form) throws ParseException {
		orderService.order(form);
		return "order_finished";
	}
}
