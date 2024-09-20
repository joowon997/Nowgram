package com.nowjoo.nowgram.comment.service;

import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.comment.domain.Comment;
import com.nowjoo.nowgram.comment.respository.CommentRepository;

@Service
public class CommemtService {

	private CommentRepository commentRepository;
	
	public CommemtService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
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
}
