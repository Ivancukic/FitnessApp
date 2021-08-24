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
public class FileDBTrainerService {

	private FileDBTrainerRepository fileDBTrainerRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\Trainer\\";

	
	@Autowired
	public FileDBTrainerService(FileDBTrainerRepository fileDBTrainerRepository) {
		this.fileDBTrainerRepository = fileDBTrainerRepository;
	}
	
	public FileDBTrainer store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBTrainer fileDB = new FileDBTrainer (fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBTrainerRepository.save(fileDB);
	}
	
	public FileDBTrainer getFile(String id) {
		
		return fileDBTrainerRepository.getById(id);
	}
	
	public Stream<FileDBTrainer> getAllFiles() {
		
		return fileDBTrainerRepository.findAll().stream();
	}
	
}
