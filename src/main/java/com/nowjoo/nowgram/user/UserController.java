package com.nowjoo.nowgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/nowgram/user")
@Controller
public class UserController {

	@GetMapping("/login-view")
	public String inputLogin() {
		return"user/login";
	}

	@GetMapping("/join-view")
	public String inputJoin() {
		return"user/join";
	}

	@GetMapping("/find-view")
	public String inputfind() {
		return"user/find";
	}

	@GetMapping("/profil-view")
	public String userProfil() {
		
		return"user/profil";
	}
	
	// 로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/nowgram/user/login-view";
	}
}
