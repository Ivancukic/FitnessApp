package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.Trainings;

public interface TrainingsService {
	
	public List<Trainings> findAll();
	public Trainings findById(Long id);
	public void save(Trainings trainings);
	public void deleteById(Long id);

}
