package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ShowItemDetailService;

@Controller
@RequestMapping("/showdetail")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@RequestMapping("/showItemDetail")
	public String showItemDetail(Integer itemId, Model model) {
		
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
