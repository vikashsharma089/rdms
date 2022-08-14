package com.rdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.RationDistribution;
import com.rdms.repository.RationDistributionRepository;

@Service
public class RationDistributionService {
	
	@Autowired
	private RationDistributionRepository rationDistributionRepository;
	@Autowired
	private UserService userService;
	
	public RationDistribution save(RationDistribution model) {
		return rationDistributionRepository.save(model);
	}
	
	public List<RationDistribution> findByStockId(Integer stockId) {
		Integer villageId = userService.getVillage().getID();
		return rationDistributionRepository.findByStockId(stockId,villageId);
	}
	
	public List<RationDistribution> findByStockAndRationCardId(Integer stockId,Integer rationCardId) {
		 Integer villageId = userService.getVillage().getID();
		return rationDistributionRepository.findByStockAndRationCardId(stockId, rationCardId, villageId);
	}

	public List<String> findDistributedCardByStockAndVillageId(Integer stockId) {
		Integer villageId =userService.getVillage().getID();
		return rationDistributionRepository.findDistributedCardByStockAndVillageId(stockId, villageId);
	}
	
}
