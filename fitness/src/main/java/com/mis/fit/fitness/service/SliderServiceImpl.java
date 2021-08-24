package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.SliderRepository;
import com.mis.fit.fitness.entity.Slider;

@Service
public class SliderServiceImpl implements SliderService {
	
	private SliderRepository sliderRepository;
	
	
	@Autowired
	public SliderServiceImpl(SliderRepository sliderRepository) {
		
		this.sliderRepository = sliderRepository;
	}

	@Override
	public List<Slider> findAll() {
		
		return sliderRepository.findAll();
	}

	@Override
	public Slider findById(Long id) {
		
		Optional<Slider> result = sliderRepository.findById(id);
		
		Slider slider = null;
		
		if(result.isPresent()) {
			
			slider = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return slider;
	}

	@Override
	public void save(Slider slider) {
		
		sliderRepository.save(slider);
	}

	@Override
	public void deleteById(Long id) {
		
		sliderRepository.deleteById(id);
	}

}
