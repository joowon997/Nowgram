package com.nowjoo.nowgram.user.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfilDTO {

	private int userId;
	private int profilId;
	private int postId;
	
	private String profilImagePath;
	private String postImagePath;
	
	private int postCount;
}
