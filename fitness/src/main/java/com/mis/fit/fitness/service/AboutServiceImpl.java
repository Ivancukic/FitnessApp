package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.AboutRepository;
import com.mis.fit.fitness.entity.About;

@Service
public class AboutServiceImpl implements AboutService {
	
	private AboutRepository aboutRepository;
	
	
	@Autowired
	public AboutServiceImpl(AboutRepository aboutRepository) {
		
		this.aboutRepository = aboutRepository;
	}

	@Override
	public List<About> findAll() {
		
		return aboutRepository.findAll();
	}

	@Override
	public About findById(Long id) {
		
		Optional<About> result = aboutRepository.findById(id);
		
		About about = null;
		
		if(result.isPresent()) {
			
			about = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return about;
	}

	@Override
	public void save(About about) {
		
		aboutRepository.save(about);
	}

	@Override
	public void deleteById(Long id) {
		
		aboutRepository.deleteById(id);
	}

}
