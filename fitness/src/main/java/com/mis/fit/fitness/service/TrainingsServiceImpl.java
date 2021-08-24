package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.TrainingsRepository;
import com.mis.fit.fitness.entity.Trainings;

@Service
public class TrainingsServiceImpl implements TrainingsService {
	
	public TrainingsRepository trainingsRepository;
	
	
	@Autowired
	public TrainingsServiceImpl(TrainingsRepository trainingsRepository) {
		
		this.trainingsRepository = trainingsRepository;
	}


	@Override
	public List<Trainings> findAll() {
		
		return trainingsRepository.findAll();
	}


	@Override
	public Trainings findById(Long id) {
		
		Optional<Trainings> result = trainingsRepository.findById(id);
		
		Trainings trainings = null;
		
		if(result.isPresent()) {
			
			trainings = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return trainings;
	}


	@Override
	public void save(Trainings trainings) {
		
		trainingsRepository.save(trainings);
	}


	@Override
	public void deleteById(Long id) {
		
		trainingsRepository.deleteById(id);
	}

	
}
