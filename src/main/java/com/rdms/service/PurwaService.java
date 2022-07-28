package com.rdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.PurwaModel;
import com.rdms.repository.PurwaRepository;

@Service
public class PurwaService {
	
	@Autowired
	private PurwaRepository purwaRepository;

	public PurwaModel save(PurwaModel model) {
		return purwaRepository.save(model);
	}
}
