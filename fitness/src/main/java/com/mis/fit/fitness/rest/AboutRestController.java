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

import com.mis.fit.fitness.entity.About;
import com.mis.fit.fitness.files.FileDBAboutService;
import com.mis.fit.fitness.service.AboutService;

@Controller
@RequestMapping("/about")
public class AboutRestController {
	
	private AboutService aboutService;
	private FileDBAboutService fileDBAboutService;

	
	@Autowired
	public AboutRestController(AboutService aboutService, FileDBAboutService fileDBAboutService) {
		this.aboutService = aboutService;
		this.fileDBAboutService = fileDBAboutService;
	}

	@GetMapping("/list")
	public String listAbout(Model model) {
		
		List<About> list = aboutService.findAll();
		
		model.addAttribute("aboutList", list);
		
		return "about/about-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		About about = new About();
		
		model.addAttribute("about", about);
		
		return "about/about-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("aboutId") Long longId, Model model) {
		
		About about = aboutService.findById(longId);
		
		model.addAttribute("about", about);
		
		return "about/about-form";
	}
	
	@PostMapping("/save")
	public String saveAbout(@ModelAttribute("about") About about, @RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		about.setPicture(fileName);
		
		aboutService.save(about);
		fileDBAboutService.store(file);
		
		return "redirect:/about/list";
	}
	
	@PostMapping("/delete")
	public String deleteAbout(@RequestParam("aboutId") Long theId) {
		
		aboutService.deleteById(theId);
		
		return "redirect:/about/list";
	}
	

}
