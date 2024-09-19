package com.nowjoo.nowgram.user;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowgram.user.DTO.FindDTO;
import com.nowjoo.nowgram.user.domain.User;
import com.nowjoo.nowgram.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/nowgram/user")
public class UserRestController {

	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	} 
	
	// 아이디 찾기 기능
//	@GetMapping("/find")
//	public Map<String, String> findUser(
//			@RequestParam("name") String name
//			, @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy년 M월 d일") LocalDate birthday
//			, @RequestParam("phoneNumber") String phoneNumber
//			){
//		List<FindDTO> resultList = userService.findUser(name, birthday, phoneNumber);
//		
//		Map<String, String> resultMap = new HashMap<>();
//		
//		return resultMap;
//	}
	
	// 프로필 수정기능
	@PostMapping("/profil")
	public Map<String, String> creatProfil(
			@RequestParam("nickname") String nickname
			, @RequestParam("imageFile") MultipartFile file
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = userService.addProfil(userId, nickname, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 로그인 기능
	@PostMapping("/login")
	public Map<String, String> userLogin(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpSession session){
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success");
			
			// 사용자 이름 및 정보
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getLoginId());
			
		}else {
			resultMap.put("result", "fail");
		}
	
		return resultMap;
		}
	
	// 회원가입 기능
	@PostMapping("/join")
	public Map<String, String> adduser(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy년 M월 d일") LocalDate birthday
			,@RequestParam("email") String email
			,@RequestParam("phoneNumber") String phoneNumber
			){
		
		int count = userService.adduser(loginId, password, name, birthday, email, phoneNumber);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 중복확인
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicate(@RequestParam("loginId") String loginId){
		
		boolean isDuplicate = userService.isDuplicateId(loginId);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isDuplicate", isDuplicate);
		
		return resultMap;
	}
	
}
