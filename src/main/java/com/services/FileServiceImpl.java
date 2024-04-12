package com.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.exceptions.BadApiRequestException;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString();
		String extention = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileNameWithExtention = fileName + extention;
		String fullPathWithExtention = path + fileNameWithExtention;

		if (extention.equalsIgnoreCase(".png") || extention.equalsIgnoreCase(".jpg")
				|| extention.equalsIgnoreCase(".jpeg")) {

			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			Files.copy(file.getInputStream(), Paths.get(fullPathWithExtention));
			return fileNameWithExtention;

		} else {
			throw new BadApiRequestException();
		}

	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		String fullPath = path + File.separator + name;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
