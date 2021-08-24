package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.GalleryRepository;
import com.mis.fit.fitness.entity.Gallery;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	private GalleryRepository galleryRepository;
	

	public GalleryServiceImpl(GalleryRepository galleryRepository) {
		this.galleryRepository = galleryRepository;
	}

	@Override
	public List<Gallery> findAll() {
		
		return galleryRepository.findAll();
	}

	@Override
	public Gallery findById(Long id) {

		Gallery gallery = null;
		
		Optional<Gallery> result = galleryRepository.findById(id);
		
		if(result.isPresent()) {
			
			gallery = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		
		return gallery;
	}

	@Override
	public void save(Gallery gallery) {
		
		galleryRepository.save(gallery);
	}

	@Override
	public void deleteById(Long id) {

		galleryRepository.deleteById(id);
	}

}
