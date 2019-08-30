package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class showItemDetailController {
	
	@RequestMapping("/")
	public String index() {
		return "item_list";
	}

}
