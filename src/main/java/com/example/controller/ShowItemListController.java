package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.Hidden;
import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.service.ShowItemListService;


@Controller
@RequestMapping("/index")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@RequestMapping("/showItemList")
	public String showItemList(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		
		List<List<Item>> itemList = showItemListService.findAllItems();
		
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	@RequestMapping("/serch")
	public String serchShowItemList(String name,Model model,@AuthenticationPrincipal LoginUser loginUser) {
		List<List<Item>> itemList = showItemListService.serchShowItemList(name);
		if(Hidden.hidden(name)){return Hidden.hidden();};
		
		if(itemList.size() == 0) {
			model.addAttribute("serchError","※該当する商品がありません");
			return showItemList(model,loginUser);
		}
		model.addAttribute("itemList",itemList);
		return "item_list";
	}
}