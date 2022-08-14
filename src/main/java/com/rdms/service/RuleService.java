package com.rdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.Rules;
import com.rdms.repository.RuleRepository;

@Service
public class RuleService {

	@Autowired 
	private RuleRepository ruleRepository;
	@Autowired
	private UserService userService;
	public Rules save(Rules model) {
		return ruleRepository.save(model);
	}
	
	public List<Rules> findByVillageId() {
		return ruleRepository.findByVillageId(userService.getVillage().getID());
	}
	
}
