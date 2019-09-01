package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderItemService;

@Controller
@RequestMapping("order")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "order_confirm";
	}
	
	@RequestMapping("load")
	public String load(String id, Model model) {
		System.out.println(id);
		Order order = orderItemService.load(Integer.parseInt(id));
		model.addAttribute("order", order);
		return "order_finished";
	}
	
	@RequestMapping("update")
	public String update(OrderForm form, Model model) {
		Order order = new Order();
		order.setId(form.getIntId());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationName(form.getDestinationName());
		order.setDestinationTel(form.getDestinationTel());
		order.setDestinationZipcode(form.getDestinationZipcode());
		orderItemService.update(order);
		return "redirect:/order/load";
	}

}
