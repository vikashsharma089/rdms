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

	@Autowired
	private UserService userService;
	
	
	public void saveAll(List<RationCardModel> models) {
		
		rationCardRepository.saveAll(models);
		
	}
	
	public RationCardModel save(RationCardModel  model) {
		return rationCardRepository.save(model);
	}

	public List<String> getAllRationCardNumberByVillageId(Integer villageId){
		villageId =userService.getVillage().getID();
		return rationCardRepository.getAllRationCardNumberByVillageId(villageId);
	}
	
	public List<RationCardModel> getAllRationCardByVillageId(){
		Integer villageId =userService.getVillage().getID();
		return rationCardRepository.getAllRationCardByVillageId(villageId);
	}
	
	public List<RationCardModel> searchRationCard(String searchKeyword){
		Integer villageId = userService.getVillage().getID();
		return rationCardRepository.searchRationCard(searchKeyword,villageId);
	}
	
	public List<RationCardModel> getAllRemaingRationCard(List<String> cardList){
		Integer villageId = userService.getVillage().getID();
		if(cardList.isEmpty()){
			return rationCardRepository.getAllRationCardByVillageId(villageId);
		}
		return rationCardRepository.getAllRemaingRationCard(cardList, villageId);
	}
}
