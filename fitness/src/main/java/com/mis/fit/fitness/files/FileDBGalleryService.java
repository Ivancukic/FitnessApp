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
public class FileDBGalleryService {
	
	private FileDBGalleryRepository fileDBGalleryRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\Gallery\\";

	
	@Autowired
	public FileDBGalleryService(FileDBGalleryRepository fileDBGalleryRepository) {
		this.fileDBGalleryRepository = fileDBGalleryRepository;
	}
	
	public FileDBGallery store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBGallery fileDB = new FileDBGallery(fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBGalleryRepository.save(fileDB);
	}
	
	public FileDBGallery getFile(String id) {
		
		return fileDBGalleryRepository.getById(id);
	}
	
	public Stream<FileDBGallery> getAllFiles() {
		
		return fileDBGalleryRepository.findAll().stream();
	}
	
	

}
