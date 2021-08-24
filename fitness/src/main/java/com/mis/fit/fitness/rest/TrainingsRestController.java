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

import com.mis.fit.fitness.entity.Trainings;
import com.mis.fit.fitness.files.FileDBTrainingsService;
import com.mis.fit.fitness.service.TrainingsService;

@Controller
@RequestMapping("/trainings")
public class TrainingsRestController {
	
	private TrainingsService trainingsService;
	private FileDBTrainingsService fileDBTrainingsService;

	
	@Autowired
	public TrainingsRestController(TrainingsService trainingsService, FileDBTrainingsService fileDBTrainingsService) {
		this.trainingsService = trainingsService;
		this.fileDBTrainingsService = fileDBTrainingsService;
	}

	@GetMapping("/list")
	public String listTrainings(Model model) {
		
		List<Trainings> list = trainingsService.findAll();
		
		model.addAttribute("listTrainings", list);
		
		return "trainings/trainings-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Trainings trainings = new Trainings();
		
		model.addAttribute("trainings", trainings);
		
		return "trainings/trainings-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("trainingsId") Long longId, Model model) {
		
		Trainings trainings = trainingsService.findById(longId);
		
		model.addAttribute("trainings", trainings);
		
		return "trainings/trainings-form";
	}
	
	@PostMapping("/save")
	public String saveTrainings(@ModelAttribute("trainings") Trainings trainings, @RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		trainings.setPicture(fileName);
		
		trainingsService.save(trainings);
		
		fileDBTrainingsService.store(file);
		
		return "redirect:/trainings/list";
	}
	
	@PostMapping("/delete")
	public String deleteTrainings(@RequestParam("trainingsId") Long theId) {
		
		trainingsService.deleteById(theId);
		
		return "redirect:/trainings/list";
	}
	
	

}
