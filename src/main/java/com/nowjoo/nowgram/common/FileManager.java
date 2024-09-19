package com.nowjoo.nowgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	// 상수
		public static final String FILE_UPLOAD_PATH = "D:\\jung\\springProject\\upload\\nowgram";
		
		// 파일 저장
		public static String saveFile(int userId, MultipartFile file) {
			
			if (file == null) {
				return null;
			}
			
			// 같은 파일이름으로 전달 될 경우
			// 폴더를 만들어서 파일 저장
			// 로그인 사용자 userId를 폴더 이름으로 사용
			// 현재 시간정보를 폴더 이름으로 사용
			// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 milli second (1 / 1000초) 로 표현한 값
			// ex ) 2_938091328
			
			String directoryName = "/" + userId + "_" + System.currentTimeMillis();
			
			// 폴더 생성
			String directoryPath = FILE_UPLOAD_PATH + directoryName;
			
			File directory = new File(directoryPath);
			
			if(!directory.mkdir()) {
				// 폴더 생성 실패
				
				return null;
			}
			
			// 파일 저장
			String filePath = directoryPath + "/" + file.getOriginalFilename();
			
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(filePath);
				Files.write(path, bytes);
			} catch (IOException e) {
				// 파일 저장 실패
				return null;
			}
			
			// 저장된 파일을 접근할 URL path 만들기
			// 파일 저장 경로 : "D:\\jung\\springProject\\upload\\memo/2_8120980/test.png"; 저장되는 경로
			// URL path : /image/2_8120980/test.png
			
			return "/images" + directoryName + "/" + file.getOriginalFilename();
		}
	
}
