package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.TrainerRepository;
import com.mis.fit.fitness.entity.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService {

	private TrainerRepository trainerRepository;
	
	
	@Autowired
	public TrainerServiceImpl(TrainerRepository trainerRepository) {
		this.trainerRepository = trainerRepository;
	}
	
	

	@Override
	public List<Trainer> findAll() {
		
		return trainerRepository.findAll();
	}

	@Override
	public Trainer findById(Long id) {
		
		Optional<Trainer> result = trainerRepository.findById(id);
		
		Trainer trainer = null;
		
		if(result.isPresent()) {
			
			trainer = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return trainer;
	}

	@Override
	public void save(Trainer trainer) {
		
		trainerRepository.save(trainer);
	}

	@Override
	public void deleteById(Long id) {
		
		trainerRepository.deleteById(id);
	}

}
