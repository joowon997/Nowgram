package com.nowjoo.nowgram.profil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowgram.post.dto.ProfilPostDTO;
import com.nowjoo.nowgram.profil.dto.ProfilDTO;
import com.nowjoo.nowgram.profil.service.ProfilService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nowgram/user")
public class ProfilController {

	private ProfilService profilService;
	
	public ProfilController(ProfilService profilService) {
		this.profilService = profilService;
	}
	
	
	@GetMapping("/profil-view")
	public String userProfil(
			Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		ProfilDTO profilDTO = profilService.getProfil(userId);
		
		model.addAttribute("profil", profilDTO);
		
		return"post/profil";
	}
}
