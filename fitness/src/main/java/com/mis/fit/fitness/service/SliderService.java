package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.Slider;

public interface SliderService {
	
	public List<Slider> findAll();
	public Slider findById(Long id);
	public void save(Slider slider);
	public void deleteById(Long id);

}
