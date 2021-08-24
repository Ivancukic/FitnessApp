package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.Gallery;

public interface GalleryService {
	
	public List<Gallery> findAll();
	public Gallery findById(Long id);
	public void save(Gallery gallery);
	public void deleteById(Long id);

}
