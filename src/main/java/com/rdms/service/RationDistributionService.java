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
	
	public RationDistribution save(RationDistribution model) {
		return rationDistributionRepository.save(model);
	}
	
	public List<RationDistribution> findByStockId(Integer stockId) {
		return rationDistributionRepository.findByStockId(stockId);
	}
	
	public List<RationDistribution> findByStockAndRationCardId(Integer stockId,Integer rationCardId) {
		return rationDistributionRepository.findByStockAndRationCardId(stockId, rationCardId);
	}

	public List<String> findDistributedCardByStockAndVillageId(Integer stockId,Integer villageId) {
		return rationDistributionRepository.findDistributedCardByStockAndVillageId(stockId, villageId);
	}
	
}
