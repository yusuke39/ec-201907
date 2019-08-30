package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.showItemDetailService;

@Controller
@RequestMapping("")
public class showItemDetailController {
	
	@Autowired
	private showItemDetailService service;
	
	@RequestMapping("/showItemDetail")
	public String showItemDetail(Integer itemId, Model model) {
		
		Item showItemDetail = service.findItem(itemId);
		
		model.addAttribute("showItemDetail", showItemDetail);
		
		return "item_detail";
	}

}
