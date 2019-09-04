package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderService;



/**
 * 注文履歴を表示するコントローラー.
 * @author hirokiokazaki
 *
 */
@Controller
@RequestMapping("/orderHistory")
public class ShowUserOrderHistoryController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 注文履歴を表示
	 * @param model
	 * @return order_history.htmlを呼び出し
	 */
	@RequestMapping("/list")
	public String load(Model model) {
		List<Order> orderHistoryList = orderService.load();
		model.addAttribute("orderHistoryList", orderHistoryList);
		return "order_history";
	}
}
