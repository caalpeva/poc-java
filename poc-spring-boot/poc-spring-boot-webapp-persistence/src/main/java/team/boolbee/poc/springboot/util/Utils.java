package team.boolbee.poc.springboot.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utils {

	
	public static String saveFile(MultipartFile multipartFile, String path) {		
		String fileName = multipartFile.getOriginalFilename();
		try {
			fileName = generatePrefix(8) + fileName.replace(" ", "-");
			File file = new File(path, fileName);
			System.out.print(file.getAbsolutePath());
			multipartFile.transferTo(file);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
		
		return fileName;
	}
	
	private static String generatePrefix(int num) {
		String characteres="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < num; i ++) {
			int value = (int) (Math.random() * characteres.length() - 1);
			sb.append(characteres.charAt(value));
		}
		
		return sb.toString();
	}
}
