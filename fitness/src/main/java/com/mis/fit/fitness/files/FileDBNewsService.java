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
public class FileDBNewsService {

	private FileDBNewsRepository fileDBNewsRepository;
	
	private static String UPLOADED_FOLDER = "C:\\Users\\Ivan\\Desktop\\PROGRAMIRANJE\\Java\\MySites\\fitness\\src\\main\\resources\\static\\images\\news\\";

	@Autowired
	public FileDBNewsService(FileDBNewsRepository fileDBNewsRepository) {
		this.fileDBNewsRepository = fileDBNewsRepository;
	}
	
	public FileDBNews store(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		FileDBNews fileDB = new FileDBNews(fileName, file.getContentType(), file.getBytes());
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		
		return fileDBNewsRepository.save(fileDB);
	}
	
	public FileDBNews getFile(String id) {
		
		return fileDBNewsRepository.getById(id);
	}
	
	public Stream<FileDBNews> getAllFiles() {
		
		return fileDBNewsRepository.findAll().stream();
	}

	
}
