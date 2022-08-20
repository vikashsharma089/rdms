package com.rdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.PurwaModel;
import com.rdms.repository.PurwaRepository;

import java.util.List;

@Service
public class PurwaService {
	
	@Autowired
	private PurwaRepository purwaRepository;

	@Autowired
	private UserService userService;

	public PurwaModel save(PurwaModel model) {
		if(model.getVillage().getID() ==null){
			model.setVillage(userService.getVillage());
			return purwaRepository.save(model);
		}
		return purwaRepository.save(model);
	}

	public List<PurwaModel> findAllByVillage(){
		return  purwaRepository.findAllByVillage(userService.getVillage());
	}
}

