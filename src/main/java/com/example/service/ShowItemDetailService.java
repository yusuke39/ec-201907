package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppringRepository;

@Service
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppringRepository toppringRepository;
	
	
	
	public Item findItem(Integer itemId) {
		
		List<Item> showItemDetailList = itemRepository.load(itemId);
		
		if(showItemDetailList.size() == 0) {
			return null;
		}
		
		Item itemDetail = showItemDetailList.get(0);
		
		return itemDetail;
	}
	
	
	
	
	public List<Topping> findoppings() {
		List<Topping> showToppingDetaolList = toppringRepository.findAlltoppings();
		
		return showToppingDetaolList;
	}

}
