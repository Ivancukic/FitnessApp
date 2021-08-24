package com.mis.fit.fitness.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileDBSliderService {
	
	private FileDBSliderRepository fileDBSliderRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\slider\\";

	@Autowired
	public FileDBSliderService(FileDBSliderRepository fileDBSliderRepository) {
		this.fileDBSliderRepository = fileDBSliderRepository;
	}
	
	public FileDBSlider store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBSlider fileDB = new FileDBSlider(fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBSliderRepository.save(fileDB);
	}
	
	public FileDBSlider getFile(String id) {
		
		return fileDBSliderRepository.getById(id);
	}
	
	public Stream<FileDBSlider> getAllFiles() {
		
		return fileDBSliderRepository.findAll().stream();
	}
	

}
