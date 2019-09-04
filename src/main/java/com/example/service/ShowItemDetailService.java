package com.example.service;

import java.util.ArrayList;
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
	
	
	
	
	public List<List<Topping>> findToppings() {
		List<Topping> showToppingDetaolList = toppringRepository.findAlltoppings();
		return arrayToppingList(showToppingDetaolList);
	}
	
	
	public List<List<Topping>> arrayToppingList(List<Topping> topping) {
		
		List<Topping> topping3List = new ArrayList<>();
		
		List<List<Topping>> toppingAllList = new ArrayList<>();
		
		for (int i = 1; i <= topping.size(); i++) {

			topping3List.add(topping.get(i - 1));

			if ((i % 3) == 0) {
				toppingAllList.add(topping3List);
				topping3List = new ArrayList<>();
			}
			
		}
		if(topping3List.size() == 0){
			return toppingAllList;
		}
		toppingAllList.add(topping3List);
		return toppingAllList;
	}
}
	
