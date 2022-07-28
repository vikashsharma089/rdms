package com.rdms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.rdms.model.YearModel;
import com.rdms.repository.YearRepository;

@Service
public class YearService {

	@Autowired
	private YearRepository yearRepository; 
	
	public YearModel save(YearModel model) {
		return yearRepository.save(model);
	}
	
	public Optional<YearModel> findById(Integer id){
		return yearRepository.findOne(null);
	}
	
}
