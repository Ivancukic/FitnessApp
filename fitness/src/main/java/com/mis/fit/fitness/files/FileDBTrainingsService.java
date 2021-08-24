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
public class FileDBTrainingsService {

	private FileDBTrainingsRepository fileDBTrainingsRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\Trainings\\";

	@Autowired
	public FileDBTrainingsService(FileDBTrainingsRepository fileDBTrainingsRepository) {
		this.fileDBTrainingsRepository = fileDBTrainingsRepository;
	}
	
	public FileDBTrainings store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBTrainings fileDB = new FileDBTrainings (fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBTrainingsRepository.save(fileDB);
	}
	
	public FileDBTrainings getFile(String id) {
		
		return fileDBTrainingsRepository.getById(id);
	}
	
	public Stream<FileDBTrainings> getAllFiles() {
		
		return fileDBTrainingsRepository.findAll().stream();
	}
	
	
}
