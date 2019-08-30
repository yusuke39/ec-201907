package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> feature/showItemDetail

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

}
