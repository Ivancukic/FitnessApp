package com.mis.fit.fitness.front;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mis.fit.fitness.entity.About;
import com.mis.fit.fitness.entity.Gallery;
import com.mis.fit.fitness.entity.Message;
import com.mis.fit.fitness.entity.News;
import com.mis.fit.fitness.entity.Slider;
import com.mis.fit.fitness.entity.Trainer;
import com.mis.fit.fitness.entity.Trainings;
import com.mis.fit.fitness.service.AboutService;
import com.mis.fit.fitness.service.GalleryService;
import com.mis.fit.fitness.service.MessageService;
import com.mis.fit.fitness.service.NewsService;
import com.mis.fit.fitness.service.SliderService;
import com.mis.fit.fitness.service.TrainerService;
import com.mis.fit.fitness.service.TrainingsService;

@Controller
@RequestMapping("/")
public class FrontControllor {
	
	private SliderService sliderService;
	private AboutService aboutService;
	private TrainingsService trainingsService;
	private NewsService newsService;
	private TrainerService trainerService;
	private GalleryService galleryService;
	private MessageService messageService;
	


	@Autowired
	public FrontControllor(SliderService sliderService, AboutService aboutService, TrainingsService trainingsService,
			NewsService newsService, TrainerService trainerService, GalleryService galleryService,
			MessageService messageService) {
		this.sliderService = sliderService;
		this.aboutService = aboutService;
		this.trainingsService = trainingsService;
		this.newsService = newsService;
		this.trainerService = trainerService;
		this.galleryService = galleryService;
		this.messageService = messageService;
	}



	@GetMapping({"/", "", "index"})
	public String getIndexPage(Model model) {
		
		// slider
		List<Slider> list = sliderService.findAll(); // ALL SLIDE
		Slider slider = list.get(0); // ONLY FIRST SLIDE
		List<Slider> photos = new ArrayList<>(); // ONLY PHOTOS BUT NOT FIRST!
		
		for(int i=1; i<list.size(); i++) {
			
			photos.add(list.get(i));
		}
		
		// about
		List<About> aboutList = aboutService.findAll();
		About about = aboutList.get(aboutList.size()-1); // PRIKAZUJE SAMO POSLEDNJI UNOS
		
		// service
		List<Trainings> trainingsList = trainingsService.findAll();
		Trainings trainings = trainingsList.get(trainingsList.size()-1); // POSLEDNJI UNESEN TRENING KOJI PRIKAZUJE NASLOV I OPIS
		List<Trainings> trainingsPic = new ArrayList<>(); // SAMO TRI TRENINGA NA Index STRANU
		
		for(int i=trainingsList.size()-1; i>trainingsList.size()-4; i--) {
			
			trainingsPic.add(trainingsList.get(i));
		}
		
		// news
		List<News> newsList = newsService.findAll();
		News news = newsList.get(newsList.size()-1); // POSLEDNJA VEST SE POSTAVLJA
		
		// trainers
		List<Trainer> trainerList = trainerService.findAll();
		List<Trainer> trainerOnIndexList = new ArrayList<>(); // TRENERI KOJI IDU NA Index
		
		for(int i=0; i<trainerList.size(); i++) {
			
			if(trainerList.get(i).getAtIndexPage() == true) {
				
				trainerOnIndexList.add(trainerList.get(i));
			}
		}
		
		List<Trainer> trainerOnIndexLastThree = new ArrayList<>(); // OD SVIH NA INDEX SAMO POSLEDNJA TRI 
		
		for(int i=trainerOnIndexList.size()-1; i>trainerOnIndexList.size()-4; i--) {
			
			trainerOnIndexLastThree.add(trainerOnIndexList.get(i));
		}
		
		Collections.reverse(trainerOnIndexLastThree);
		
		// gallery
		List<Gallery> galleryList = galleryService.findAll(); // SVE SLIKE
		List<Gallery> galleryListLastSix = new ArrayList<>(); // SAMO POSLEDNJIH SEST SLIKA
		
		for(int i=galleryList.size()-1; i>galleryList.size()-7; i--) {
			
			galleryListLastSix.add(galleryList.get(i));
		}
		
		Collections.reverse(galleryListLastSix);
		
		Gallery gallery = galleryList.get(galleryList.size()-1);
		
		
		model.addAttribute("sliders", list);
		model.addAttribute("firstSlide", slider);
		model.addAttribute("photos", photos);
		
		model.addAttribute("about", about);
		
		model.addAttribute("trainings", trainings);
		model.addAttribute("trainingsPicIndex", trainingsPic);
		
		model.addAttribute("news", news);
		
		model.addAttribute("trainerOnIndex", trainerOnIndexLastThree);
		
		model.addAttribute("galleryListOnIndex", galleryListLastSix);
		model.addAttribute("galleryLastDescription", gallery);
		
		
		model.addAttribute("messageOnIndex", new Message());
		
		return "/index";
	}
	
	@PostMapping("message-save")
	public String saveMessage(@ModelAttribute("message") Message message) {
		
		messageService.save(message);
		
		return "redirect:/index";
	}
	

	////////////////////////////// ABOUT BEGGINING /////////////////////////////
	
	@GetMapping("/about")
	public String getAboutPage(Model model) {
		
		List<About> aboutList = aboutService.findAll();
		About about = aboutList.get(aboutList.size()-1);
		
		model.addAttribute("about", about);
		
		return "/about";
	}
	
	////////////////////////////// ABOUT END  ////////////////////////////////////
	////////////////////////////// SERVICES BEGGINING /////////////////////////////	
	
	@GetMapping("/service")
	public String getServicePage(Model model) {
		
		List<Trainings> trainingsList = trainingsService.findAll();
		Trainings trainings = trainingsList.get(trainingsList.size()-1);
		List<Trainings> trainingsPic = new ArrayList<>();
		
		for(int i=trainingsList.size()-1; i>0; i--) {
			
			trainingsPic.add(trainingsList.get(i));
		}
		
		model.addAttribute("trainings", trainings);
		model.addAttribute("trainingsPicIndex", trainingsPic);
		
		return "/service";
	}
	
	////////////////////////////// SERVICES END  ////////////////////////////////////
	////////////////////////////// NEWS BEGGINING /////////////////////////////	
	
	@GetMapping("/news")
	public String getNewsPage(Model model) {
		
		List<News> newsList = newsService.findAll();
		News news = newsList.get(newsList.size()-1);
		
		model.addAttribute("news", news);
		
		return "/news";
	}
	
	////////////////////////////// NEWS END  ////////////////////////////////////
	////////////////////////////// TRAINER BEGGINING /////////////////////////////	
	
	@GetMapping("/trainer")
	public String getTrainerPage(Model model) {
		
		List<Trainer> trainerList = trainerService.findAll();
		
		model.addAttribute("trainerList", trainerList);
		
		return "/trainer";
	}
	
	////////////////////////////// TRAINER END  ////////////////////////////////////
	////////////////////////////// GALLERY BEGGINING /////////////////////////////	
	
	@GetMapping("/gallery")
	public String getGalleryPage(Model model) {
		
		List<Gallery> galleryList = galleryService.findAll();
		Gallery gallery = galleryList.get(galleryList.size()-1);
		
		model.addAttribute("galleryList", galleryList);
		model.addAttribute("galleryLastDescription", gallery);
		
		return "/gallery";
	}
	
	////////////////////////////// GALLERY END  ////////////////////////////////////
	////////////////////////////// CONTACT BEGGINING /////////////////////////////
	
	@GetMapping("/contact")
	public String getContactPage(Model model) {
		
		model.addAttribute("messageOnIndex", new Message());
		
		return "/contact";
	}
	
	////////////////////////////// CONTACT END  ////////////////////////////////////

}
