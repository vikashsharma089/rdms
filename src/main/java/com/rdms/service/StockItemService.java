package com.rdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.StockItem;
import com.rdms.repository.StockItemRepository;

@Service
public class StockItemService {

	@Autowired 
	private StockItemRepository stockItemRepository;
	@Autowired
	private UserService userService;
	
	public StockItem save(StockItem model) {
		return stockItemRepository.save(model);
	}
	
	public List<StockItem> findAllByVillage() {

		return stockItemRepository.findAllByVillage(userService.getVillage().getID());
	}
}
