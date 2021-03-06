package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.form.ShoppingCartForm;
import com.example.service.ShowItemDetailService;

/**
 * おもちゃ詳細画面.
 * 
 * @author hiranoyuusuke
 *
 */
@Controller
@RequestMapping("/showdetail")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@ModelAttribute
	public ShoppingCartForm setUpForm() {
		return new ShoppingCartForm();
	}
	
	/**
	 * おもちゃ詳細を表示する
	 * 
	 * @param itemId
	 * @param model
	 * @return おもちゃ詳細表示画面
	 */
	@RequestMapping("/showItemDetail")
	public String showItemDetail(ShoppingCartForm form, Integer itemId, Model model) {
	
		form.setQuantity("1");
		
		Item itemDetail = showItemDetailService.findItem(itemId);
		
		if(itemDetail == null) {
			model.addAttribute("error", "おもちゃはありません");
			return "forward:";
		}
		
		List<List<Topping>> toppingAllList = showItemDetailService.findToppings();
		model.addAttribute("toppingAllList", toppingAllList);
		model.addAttribute("itemDetail", itemDetail);
		
		return "item_detail";
	}
}
