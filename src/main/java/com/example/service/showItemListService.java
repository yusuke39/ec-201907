package com.example.service;

import java.util.ArrayList;
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
	
	public List<List<Item>> findAllItems(){
		return arrayTable(itemRepository.findAll());
	}
	
	public List<List<Item>> serchShowItemList(String name){
		return arrayTable(itemRepository.findByName(name));
		
	}

	public List<List<Item>> arrayTable(List<Item> item) {

		List<Item> item3List = new ArrayList<>();

		List<List<Item>> itemAllList = new ArrayList<>();

		for (int i = 1; i <= item.size(); i++) {

			item3List.add(item.get(i - 1));

			if ((i % 3) == 0) {
				itemAllList.add(item3List);
				item3List = new ArrayList<>();
			}
			
		}
		itemAllList.add(item3List);
		return itemAllList;
	}

}
