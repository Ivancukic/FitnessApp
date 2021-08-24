package com.mis.fit.fitness.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mis.fit.fitness.dao.FileDBRepository;
import com.mis.fit.fitness.entity.FileDB;

@Service
public class FileStorageService {

	private FileDBRepository fileDBRepository;

	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\"; 
	
	@Autowired
	public FileStorageService(FileDBRepository fileDBRepository) {
		this.fileDBRepository = fileDBRepository;
	}
	
	
	public FileDB store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBRepository.save(fileDB);
	}
	
	public FileDB getFile(String id) {
		
		return fileDBRepository.getById(id);
	}
	
	public Stream<FileDB> getAllFiles() {
		
		return fileDBRepository.findAll().stream();
	}
	
	public FileDB storeInGallery(MultipartFile file) throws IOException {
		
		UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\gallery\\";
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBRepository.save(fileDB);
	}
	
	 
	
}
