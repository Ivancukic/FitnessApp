package com.mis.fit.fitness.service;

import java.util.List;

import com.mis.fit.fitness.entity.Message;

public interface MessageService {
	
	public List<Message> findAll();
	public Message findById(Long id);
	public void save(Message message);
	public void deleteById(Long id);

}
