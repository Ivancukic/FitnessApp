package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mis.fit.fitness.dao.NewsRepository;
import com.mis.fit.fitness.entity.News;

@Service
public class NewsServiceImpl implements NewsService {
	
	private NewsRepository newsRepository;
	
	
	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public List<News> findAll() {
		
		return newsRepository.findAll();
	}

	@Override
	public News findById(Long id) {
		
		Optional<News> result = newsRepository.findById(id);
		
		News news = null;
		
		if(result.isPresent()) {
			
			news = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return news;
	}

	@Override
	public void save(News news) {
		
		newsRepository.save(news);
	}

	@Override
	public void deleteById(Long id) {
		
		newsRepository.deleteById(id);
	}

}
