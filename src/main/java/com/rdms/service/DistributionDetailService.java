package com.rdms.service;

import java.util.List;
import java.util.Set;

import com.rdms.model.RationDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.DistributionDetails;
import com.rdms.repository.DistributionDetailRepository;

@Service
public class DistributionDetailService {
	
	@Autowired
	private DistributionDetailRepository distributionDetailRepository;
	
	public void saveAll(Set<DistributionDetails> models) {
		distributionDetailRepository.saveAll(models);
	}
	
	public DistributionDetails save(DistributionDetails model) {
		return distributionDetailRepository.save(model);
	}

	public Set<DistributionDetails> findDistributionDetailsByDistribution(RationDistribution rationDistribution){
		return distributionDetailRepository.findAllByDistribution(rationDistribution);
	}

}
