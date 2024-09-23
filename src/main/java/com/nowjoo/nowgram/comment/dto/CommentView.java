package com.nowjoo.nowgram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentView {

	private int commentId;
	private int userId;
	
	private String contents;
	private String loginId;
}
