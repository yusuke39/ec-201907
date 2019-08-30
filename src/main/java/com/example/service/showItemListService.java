package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;


@Service
@Transactional
public class showItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findAllItems(){
		return itemRepository.findAll();
	}
	
	public List<Item> serchShowItemList(String name){
		return itemRepository.findByName(name);
	}

}
