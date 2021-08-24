package com.mis.fit.fitness.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.fit.fitness.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}
