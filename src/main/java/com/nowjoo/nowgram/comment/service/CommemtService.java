package com.nowjoo.nowgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.comment.domain.Comment;
import com.nowjoo.nowgram.comment.dto.CommentView;
import com.nowjoo.nowgram.comment.repository.CommentRepository;
import com.nowjoo.nowgram.user.domain.User;
import com.nowjoo.nowgram.user.service.UserService;

@Service
public class CommemtService {

	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommemtService(CommentRepository commentRepository
							, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	// 댓글 추가 기능
	public Comment addComent(int postId, int userId, String comments) {
		
		Comment comment = Comment.builder()
							.postId(postId)
							.userId(userId)
							.comment(comments)
							.build();
		
		Comment result = commentRepository.save(comment);
		
		return result;
	}
	
	public List<CommentView> getCommentListByPostId(int postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentView> commentviewList = new ArrayList<>();
		
		for(Comment comment: commentList) {
			
			int userId = comment.getUserId();
			User user = userService.getUserById(userId);
			
			CommentView commentview = CommentView.builder()
										.commentId(comment.getId())
										.userId(userId)
										.contents(comment.getComment())
										.loginId(user.getLoginId())
										.build();
			commentviewList.add(commentview);
		}
		
		return commentviewList;
	}
}
