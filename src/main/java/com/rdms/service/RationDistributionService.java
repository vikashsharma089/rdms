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
	
	public List<RationDistribution> findByMonthId(Integer monthId) {
		return rationDistributionRepository.findByMonthId(monthId);
	}
	
	public List<RationDistribution> findByMonthAndRationCardId(Integer monthId,Integer rationCardId) {
		return rationDistributionRepository.findByMonthAndRationCardId(monthId, rationCardId);
	}

}
