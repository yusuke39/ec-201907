package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.showItemListService;


@Controller
@RequestMapping("/itemList")
public class showItemListController {
	
	@Autowired
	private showItemListService showItemListService;
	
	
	
	@RequestMapping("/showItemList")
	public String showItemList(Model model) {
		List<List<Item>> itemList = showItemListService.findAllItems();
		
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	@RequestMapping("/serch")
	public String serchShowItemList(String name,Model model) {
		List<List<Item>> itemList = showItemListService.serchShowItemList(name);
		
		if(itemList.size() == 0) {
			model.addAttribute("serchError","※該当する商品がありません");
			return showItemList(model);
		}
		model.addAttribute("itemList",itemList);
		return "item_list";
	}
}