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

import com.mis.fit.fitness.entity.News;
import com.mis.fit.fitness.files.FileDBNewsService;
import com.mis.fit.fitness.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsRestController {
	
	private NewsService newsService;
	
	private FileDBNewsService fileDBNewsService;
	
	
	@Autowired
	public NewsRestController(NewsService newsService, FileDBNewsService fileDBNewsService) {
		this.newsService = newsService;
		this.fileDBNewsService = fileDBNewsService;
	}

	@GetMapping("/list")
	public String listNews(Model model) {
		
		List<News> list = newsService.findAll();
		
		model.addAttribute("newsList", list);
		
		return "news/news-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		News news = new News();
		
		model.addAttribute("news", news);
		
		return "news/news-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("newsId") Long longId, Model model) {
		
		News news = newsService.findById(longId);
		
		model.addAttribute("news", news);
		
		return "news/news-form";
	}
	
	@PostMapping("/save")
	public String saveNews(@ModelAttribute("news") News news, @RequestParam MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		news.setPicture(fileName);
		
		newsService.save(news);
		
		fileDBNewsService.store(file);
		
		return "redirect:/news/list";
	}
	
	@PostMapping("/delete")
	public String deleteNews(@RequestParam("newsId") Long theId) {
		
		newsService.deleteById(theId);
		
		return "redirect:/news/list";
	}
	

}
