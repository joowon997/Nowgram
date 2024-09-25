package com.nowjoo.nowgram.post.dto;

import java.util.List;

import com.nowjoo.nowgram.comment.dto.CommentView;

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
	
	private int likeCount;
	
	private boolean isLike;
	
	private List<CommentView> commentList;
}
