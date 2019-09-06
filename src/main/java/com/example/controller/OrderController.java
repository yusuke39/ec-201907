package com.example.controller;

import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
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
		return "cart_list";
	}
	
	@RequestMapping("decision")
	public String decision(Integer orderId, Model model, OrderForm orderForm) {
		
		Order order = orderService.showDetail(orderId);
	
		//ユーザーの情報が宛先フォームに自動的に格納されるように
//		orderIdにあるuserIdをUserオブジェクトにセットし、
//		DBから取り出したユーザー情報をフォームにセット.
		User user = orderService.load(order.getUserId());
		orderForm.setDestinationName(user.getName());
		orderForm.setDestinationEmail(user.getEmail());
		orderForm.setDestinationZipcode(user.getZipcode());
		orderForm.setDestinationAddress(user.getAddress());
		orderForm.setDestinationTel(user.getTelephone());
		
		model.addAttribute("order", order);
//		model.addAttribute("user",user);
		return "order_confirm";
	}
	
	@RequestMapping("update")
	public String order(@Validated OrderForm form, BindingResult result, Model model) throws ParseException {
		
		
		//現在の日付を取得
		LocalDate localDate = LocalDate.now();
		LocalDate inputDate = LocalDate.parse(form.getDeliveryDate());
		boolean check = localDate.isBefore(inputDate);
		
		
		// 入力された日付を取得
		
		//入力された日付が本日の日付の場合
		if(localDate.equals(inputDate)) {
			result.rejectValue("deliveryDate", "","本日のお届けはできません");
		}
		
		//入力された日付が本日以前の場合
		if(check == false) {
			result.rejectValue("deliveryDate", "","現在の日付より先の日付を指定してください");
		}
		
		if(result.hasErrors()) {
		return decision(form.getIntId(), model,form);
		}

		orderService.order(form);
		model.addAttribute("form", form);
		return "order_finished";
	}
}
