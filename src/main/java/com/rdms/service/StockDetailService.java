package com.rdms.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.StockDetails;
import com.rdms.repository.StockDetailRepository;

@Service
public class StockDetailService {

	@Autowired
	private  StockDetailRepository stockDetailRepository;
	
	public void save(Set<StockDetails> model) {
		stockDetailRepository.saveAll(model);
	}
}
