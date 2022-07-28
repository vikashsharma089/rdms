package com.rdms.service;

import java.util.Set;

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

}
