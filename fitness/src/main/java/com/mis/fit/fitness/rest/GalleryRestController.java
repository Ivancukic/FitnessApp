package com.mis.fit.fitness.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mis.fit.fitness.entity.Gallery;
import com.mis.fit.fitness.files.FileDBGallery;
import com.mis.fit.fitness.files.FileDBGalleryService;
import com.mis.fit.fitness.message.ResponseFile;
import com.mis.fit.fitness.message.ResponseMessage;
import com.mis.fit.fitness.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryRestController {
	
	private GalleryService galleryService;
	private FileDBGalleryService fileGalleryService; 
	
	
	@Autowired
	public GalleryRestController(GalleryService galleryService, FileDBGalleryService fileGalleryService) {
		this.galleryService = galleryService;
		this.fileGalleryService = fileGalleryService;
	}
	
	
	@GetMapping("/list")
	public String listGallery(Model model) {
		
		List<Gallery> list = galleryService.findAll();
		
		model.addAttribute("galleryList", list);
		
		return "gallery/gallery-list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Gallery gallery = new Gallery();
		
		model.addAttribute("gallery", gallery);
		
		return "gallery/gallery-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("galleryId") Long longId, Model model) {
		
		Gallery gallery = galleryService.findById(longId);
		
		model.addAttribute("gallery", gallery);
		
		return "gallery/gallery-form";
	}
	
	@PostMapping("/save")
	public String saveGallery(@ModelAttribute("gallery") Gallery gallery, @RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		gallery.setPicture(fileName);
		
		galleryService.save(gallery);
		
		fileGalleryService.store(file);
		
		return "redirect:/gallery/list";
	}
	
	@PostMapping("/delete")
	public String deleteGallery(@RequestParam("galleryId") Long theId) {
		
		galleryService.deleteById(theId);
		
		return "redirect:/gallery/list";
	}
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		
		String message = "";
		
		try {
			
			fileGalleryService.store(file);
			
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		} catch (Exception e) {

			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		
		List<ResponseFile> files = fileGalleryService.getAllFiles().map(dbFile -> {
			
			String fileDownloadUri = ServletUriComponentsBuilder
										.fromCurrentContextPath()
										.path("/files/")
										.path(dbFile.getId())
										.toUriString();
					
			return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		
		FileDBGallery fileDB = fileGalleryService.getFile(id);
		
		return ResponseEntity.ok()
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() 	+ "\"")
							 .body(fileDB.getData());
	}

	
	
}
