package com.nowjoo.nowgram.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfilPostDTO {

	private int postId;
	
	private String postImage;
	
	private int postCount;
}
