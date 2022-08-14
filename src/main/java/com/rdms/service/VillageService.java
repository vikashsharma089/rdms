package com.rdms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.Village;
import com.rdms.repository.VillageRepository;

@Service
public class VillageService {
	
 @Autowired
 private VillageRepository villageRepository;
 
	 public Village save(Village model) {
		 return villageRepository.save(model);
	 }
	 
	 public Optional<Village> findById(Integer villagId) {
		 return villageRepository.findById(villagId);
	 }

	 public Optional<Village> findByVillageName(String villageName) {

		 return villageRepository.findByVillageName(villageName);
	}


}
