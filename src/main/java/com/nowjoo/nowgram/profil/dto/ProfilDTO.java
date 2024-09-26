package com.nowjoo.nowgram.profil.dto;

import java.util.List;

import com.nowjoo.nowgram.post.dto.ProfilPostDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfilDTO {

	private int userId;
	private int profilId;
	
	private String loginId;
	private String nikename;
	private String profilImage;
	
	private boolean isProfil;
	
	private int postCount;
	
	private List<ProfilPostDTO> postList;
}
