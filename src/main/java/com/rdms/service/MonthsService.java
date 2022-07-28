package com.rdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.MonthModel;
import com.rdms.repository.MonthsRepository;

@Service
public class MonthsService {

	@Autowired
	private MonthsRepository monthsRepository;
	
	public List<MonthModel> findAll(){
		return monthsRepository.findAll();
	}
}
