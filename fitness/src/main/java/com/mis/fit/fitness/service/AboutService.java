package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.About;

public interface AboutService {
	
	public List<About> findAll();
	public About findById(Long id);
	public void save(About about);
	public void deleteById(Long id);

}
