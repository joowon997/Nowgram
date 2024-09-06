package com.nowjoo.nowgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/login-view")
	public String inputLogin() {
		return"";
	}

	@GetMapping("/join-view")
	public String inputJoin() {
		return"";
	}
}
