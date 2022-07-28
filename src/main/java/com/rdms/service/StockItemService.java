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
	
	public StockItem save(StockItem model) {
		return stockItemRepository.save(model);
	}
	
	public List<StockItem> findAllByVillage(Integer villageId) {
		return stockItemRepository.findAllByVillage(villageId);
	}
}
