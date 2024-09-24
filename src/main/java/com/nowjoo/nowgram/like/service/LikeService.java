package com.nowjoo.nowgram.like.service;

import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.like.domain.Like;
import com.nowjoo.nowgram.like.repository.LikeRepository;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	// 좋아요 추가
	public Like addLike(int postId, int userId) {
		
		Like like = Like.builder()
					.postId(postId)
					.userId(userId)
					.build();
		
		return likeRepository.save(like);
	}
	
	
}
