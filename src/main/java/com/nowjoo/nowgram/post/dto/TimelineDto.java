package com.nowjoo.nowgram.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TimelineDto {

	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String loginId;
}
