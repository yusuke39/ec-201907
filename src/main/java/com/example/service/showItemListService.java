package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Item;
import com.example.repository.ItemRepository;



public class showItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findAllItems(){
		return itemRepository.findAll();
	}

}
