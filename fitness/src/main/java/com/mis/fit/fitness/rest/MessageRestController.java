package com.mis.fit.fitness.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mis.fit.fitness.entity.Message;
import com.mis.fit.fitness.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageRestController {
	
	private MessageService messageService;
	
	
	@Autowired
	public MessageRestController(MessageService messageService) {
		this.messageService = messageService;
	}


	
	@GetMapping("/list")
	public String listMessage(Model model) {
		
		List<Message> list = messageService.findAll();
		
		model.addAttribute("messageList", list);
		
		return "message/message-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Message message = new Message();
		
		model.addAttribute("message", message);
		
		return "message/message-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("messageId") Long longId, Model model) {
		
		Message message = messageService.findById(longId);
		
		model.addAttribute("message", message);
		
		return "message/messager-form";
	}
	
	@PostMapping("/save")
	public String saveMessage(@ModelAttribute("message") Message message) {
		
		messageService.save(message);
		
		return "redirect:/message/list";
	}
	
	@PostMapping("/delete")
	public String deleteMessage(@RequestParam("messageId") Long theId) {
		
		messageService.deleteById(theId);
		
		return "redirect:/message/list";
	}

}
