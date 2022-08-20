package com.rdms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.StockModel;
import com.rdms.repository.StockRepository;

@Service
public class StockService {

	@Autowired 
	private StockRepository stockRepository;

	@Autowired
	private UserService userService;
	
	public StockModel save(StockModel model) {
		return stockRepository.save(model);
	}
	
	public List<StockModel> loadAllMonth() {
		return (List<StockModel>)stockRepository.findAllByVillageOrderByIDDesc(userService.getVillage());
	}
	
	public List<StockModel> loadAllPendingMonth() {
		return (List<StockModel>)stockRepository.loadAllPendingMonth(userService.getVillage().getID());
	}
	
	public Optional<StockModel> findMonthById(Integer id) {
		return stockRepository.findById(id);
	}
	
	public void update(StockModel model) {
		stockRepository.save(model);
	}
}
