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

import com.mis.fit.fitness.entity.Slider;
import com.mis.fit.fitness.files.FileDBSliderService;
import com.mis.fit.fitness.service.SliderService;

@Controller
@RequestMapping("/slider")
public class SliderRestController {
	
	private SliderService sliderService;
	private FileDBSliderService fileDBSliderService; 

	
	@Autowired
	public SliderRestController(SliderService sliderService, FileDBSliderService fileDBSliderService) {
		this.sliderService = sliderService;
		this.fileDBSliderService = fileDBSliderService;
	}
	
	@GetMapping("/list")
	public String listSlider(Model model) {
		
		List<Slider> list = sliderService.findAll();
		
		model.addAttribute("sliders", list);
		
		return "slider/slider-list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Slider slider =new Slider();
		
		model.addAttribute("slider", slider);
		
		return "slider/slider-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("sliderId") Long longId, Model model) {
		
		Slider slider = sliderService.findById(longId);
		
		model.addAttribute("slider", slider);
		
		return "slider/slider-form";
	}
	
	@PostMapping("/save")
	public String saveSlider(@ModelAttribute("slider") Slider slider, @RequestParam("file") MultipartFile file) throws IOException {
		
		//String fileName = "images/slider/" + StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		slider.setImage(fileName);
		
		sliderService.save(slider);
		
		fileDBSliderService.store(file);
		
		
		return "redirect:/slider/list";
	}
	
	@PostMapping("/delete")  
	public String deleteSlider(@RequestParam("sliderId") Long theId) {
		
		sliderService.deleteById(theId);
		
		return "redirect:/slider/list";
	}

}
