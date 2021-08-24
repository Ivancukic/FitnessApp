package com.mis.fit.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mis.fit.fitness.dao.MessageRepository;
import com.mis.fit.fitness.entity.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	private MessageRepository messageRepository;
	
	
	@Autowired
	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}


	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}


	@Override
	public Message findById(Long id) {

		Optional<Message> result = messageRepository.findById(id);
		
		Message message = null;
		
		if(result.isPresent()) {
			
			message = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find!");
		}
		
		return message;
	}


	@Override
	public void save(Message message) {

		messageRepository.save(message);
	}


	@Override
	public void deleteById(Long id) {

		messageRepository.deleteById(id);
	}

	

}
