package com.example.demo.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String getFileName(String contextPath, String orifilename) {
		String filePath = contextPath;
		int pos = orifilename.lastIndexOf(".");
		String ext = orifilename.substring(pos + 1);
		String oriFile = orifilename.substring(0, pos);

		String filename = orifilename;

		File newFile = new File(filePath + "/" + filename);
		int i = 1;
		while (newFile.exists()) {
			filename = oriFile + "(" + i + ")." + ext;
			i++;
			newFile = new File(filePath + "/" + filename);
		}

		return filename;
	}

	public static String upload(String uploadDir, MultipartFile multipartFile) throws IOException {
		String fileName = getFileName(uploadDir, multipartFile.getOriginalFilename());
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

		return fileName;
	}

	public static void upload(String contextPath, List<MultipartFile> fileList,
		List<String> fileNameList) {
		String filePath = contextPath;

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}

		if (fileList != null && fileList.size() > 0) {
			for (MultipartFile multipartFile : fileList) {
				if (multipartFile.getOriginalFilename().length() == 0)//�뙆�씪�씠 �뾾�떎
					break;
				String orifilename = multipartFile.getOriginalFilename();
				String filename = getFileName(contextPath, orifilename);

				fileNameList.add(filename);

				String newFileName = filePath + "/" + filename;
				try {
					multipartFile.transferTo(new File(newFileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}


