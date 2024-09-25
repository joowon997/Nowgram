package com.nowjoo.nowgram.profil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowgram/user")
public class ProfilController {

	@GetMapping("/profil-view")
	public String userProfil() {
		
		return"user/profil";
	}
}
