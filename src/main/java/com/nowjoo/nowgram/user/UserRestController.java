package com.nowjoo.nowgram.user;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nowgram/user")
public class UserRestController {

	@PostMapping("/join")
	public Map<String, String> adduser(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy년 M월 d일") LocalDate birthday
			,@RequestParam("email") String email
			,@RequestParam("phoneNumber") String phoneNumber
			){
		
	}
	
}
