package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
public class showItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item findItem(Integer itemId) {
		
		List<Item> showItemDetailList = itemRepository.load(itemId);
		
		if(showItemDetailList.size() == 0) {
			return null;
		}
		
		Item itemDetail = showItemDetailList.get(0);
		
		return itemDetail;
	}

}
