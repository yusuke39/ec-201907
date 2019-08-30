package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.showItemDetailService;

@Controller
@RequestMapping("/showdetail")
public class showItemDetailController {
	
	@Autowired
	private showItemDetailService service;
	
	@RequestMapping("/showItemDetail")
	public String showItemDetail(Integer itemId, Model model) {
		
		Item itemDetail = service.findItem(itemId);
		
		if(itemDetail == null) {
			model.addAttribute("error", "おもちゃはありません");
			return "forward:";
		}
		
		model.addAttribute("itemDetail", itemDetail);
		
		return "item_detail";
	}

}
