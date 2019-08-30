package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
public class showItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item findItem(Integer itemId) {
		
		Item showItemDetail = itemRepository.load(itemId);
		
		return showItemDetail;
	}

}
