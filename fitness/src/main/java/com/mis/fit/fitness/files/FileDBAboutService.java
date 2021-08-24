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
public class FileDBAboutService {

	private FileDBAboutRepository fileDBAboutRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\about\\";

	
	@Autowired
	public FileDBAboutService(FileDBAboutRepository fileDBAboutRepository) {
		this.fileDBAboutRepository = fileDBAboutRepository;
	}
	
	public FileDBAbout store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBAbout fileDB = new FileDBAbout (fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBAboutRepository.save(fileDB);
	}
	
	public FileDBAbout getFile(String id) {
		
		return fileDBAboutRepository.getById(id);
	}
	
	public Stream<FileDBAbout> getAllFiles() {
		
		return fileDBAboutRepository.findAll().stream();
	}
	
	
	
}
