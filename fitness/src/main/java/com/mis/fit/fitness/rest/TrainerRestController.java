package com.mis.fit.fitness.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mis.fit.fitness.entity.Trainer;
import com.mis.fit.fitness.files.FileDBTrainerService;
import com.mis.fit.fitness.service.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerRestController {

	private TrainerService trainerService;

	private FileDBTrainerService fileDBTrainerService;
	
	
	@Autowired
	public TrainerRestController(TrainerService trainerService, FileDBTrainerService fileDBTrainerService) {
		this.trainerService = trainerService;
		this.fileDBTrainerService = fileDBTrainerService;
	}

	@GetMapping("/list")
	public String listTrainers(Model model) {
		
		List<Trainer> list = trainerService.findAll();
		
		model.addAttribute("trainers", list);
		
		return "trainer/trainer-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Trainer trainer = new Trainer();
		
		model.addAttribute("trainer", trainer);
		
		return "trainer/trainer-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("trainerId") Long longId, Model model) {
		
		Trainer trainer = trainerService.findById(longId);
		
		model.addAttribute("trainer", trainer);
		
		return "trainer/trainer-form";
	}
	
	@PostMapping("/save")
	public String saveTrainer(@ModelAttribute("trainer") Trainer trainer, @RequestParam MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		trainer.setPicture(fileName);
		
		trainerService.save(trainer);
		
		fileDBTrainerService.store(file);
		
		return "redirect:/trainer/list";
	}
	
	@PostMapping("/delete")
	public String deleteTrainer(@RequestParam("trainerId") Long theId) {
		
		trainerService.deleteById(theId);
		
		return "redirect:/trainer/list";
	}
	
	
	
	
}
