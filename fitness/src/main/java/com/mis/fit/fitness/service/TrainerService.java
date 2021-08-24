package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.Trainer;

public interface TrainerService {
	
	public List<Trainer> findAll();
	public Trainer findById(Long id);
	public void save(Trainer trainer);
	public void deleteById(Long id);

}
