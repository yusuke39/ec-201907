package com.example.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("")
	public String index(Integer id, Model model) {
		List<Order> orderList = orderService.showDetail(id);
		
//		Order order = new Order();
//		order.setId(3);
		model.addAttribute("orderList", orderList);
		return "order_confirm";
	}
	
	@RequestMapping("update")
	public String order(OrderForm form) throws ParseException {
		System.out.println();
		System.out.println(form);
		orderService.order(form);
		return "order_finished";
	}
	
//	@RequestMapping("load")
//	public String load(String id, Model model) {
//		System.out.println(id);
//		Order order = orderItemService.load(Integer.parseInt(id));
//		model.addAttribute("order", order);
//		return "order_finished";
//	}
	
//	@RequestMapping("update")
//	public String update(OrderForm form, Model model) {
//		Order order = new Order();
//		order.setId(form.getIntId());
//		order.setDestinationAddress(form.getDestinationAddress());
//		order.setDestinationEmail(form.getDestinationEmail());
//		order.setDestinationName(form.getDestinationName());
//		order.setDestinationTel(form.getDestinationTel());
//		order.setDestinationZipcode(form.getDestinationZipcode());
//		orderItemService.update(order);
//		return "redirect:/order/load";
//	}

}
