package com.rdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.RationCardModel;

import com.rdms.repository.RationCardRepository;

@Service
public class RationCardService {
	
	@Autowired 
	private RationCardRepository rationCardRepository; 
	
	
	public void saveAll(List<RationCardModel> models) {
		
		rationCardRepository.saveAll(models);
		
	}
	
	public RationCardModel save(RationCardModel  model) {
		return rationCardRepository.save(model);
	}

	public List<String> getAllRationCardNumberByVillageId(Integer villageId){
		return rationCardRepository.getAllRationCardNumberByVillageId(villageId);
	}
	
	public List<RationCardModel> getAllRationCardByVillageId(Integer villageId){
		return rationCardRepository.getAllRationCardByVillageId(villageId);
	}
	
	public List<RationCardModel> searchRationCard(String searchKeyword){
		searchKeyword = searchKeyword;
		return rationCardRepository.searchRationCard(searchKeyword);
	}
	
	public List<RationCardModel> getAllRemaingRationCard(List<String> cardList,Integer villageId){
		return rationCardRepository.getAllRemaingRationCard(cardList, villageId);
	}
}
