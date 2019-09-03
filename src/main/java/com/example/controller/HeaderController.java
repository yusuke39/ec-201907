package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/header")
public class HeaderController {
	
	@RequestMapping("/h")
	public String index() {
	return "header";
	}
}
