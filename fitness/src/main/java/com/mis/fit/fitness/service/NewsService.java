package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.News;

public interface NewsService {
	
	public List<News> findAll();
	public News findById(Long id);
	public void save(News news);
	public void deleteById(Long id);

}
