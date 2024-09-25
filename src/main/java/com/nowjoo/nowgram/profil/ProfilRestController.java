package com.nowjoo.nowgram.profil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/nowgram/user")
public class ProfilRestController {

	// 프로필 수정기능
//		@PostMapping("/profil")
//		public Map<String, String> creatProfil(
//				@RequestParam("nickname") String nickname
//				, @RequestParam("imageFile") MultipartFile file
//				, HttpSession session){
//			
//			int userId = (Integer)session.getAttribute("userId");
//			
//			int count = userService.addProfil(userId, nickname, file);
//			
//			Map<String, String> resultMap = new HashMap<>();
//			
//			if (count == 1) {
//				resultMap.put("result", "success");
//			} else {
//				resultMap.put("result", "fail");
//			}
//			
//			return resultMap;
//		}
	
}
