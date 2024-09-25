package com.nowjoo.nowgram.like.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.like.domain.Like;
import com.nowjoo.nowgram.like.repository.LikeRepository;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	// 게시물 삭제시 좋아요 삭제
	public void deleteLikeByPostId(int postId) {
		likeRepository.deleteByPostId(postId);
	}
	
	// 좋아요 취소
	public boolean deleteLike(int postId, int userId) {
		Optional<Like> optionalLike = likeRepository.findByUserIdAndPostId(userId, postId);
		Like like = optionalLike.orElse(null);
		
		if (like != null) {
			likeRepository.delete(like);
			return true;
		}else {
			return false;
		}
	}
	
	
	// 좋아요 추가
	public Like addLike(int postId, int userId) {
		
		Like like = Like.builder()
					.postId(postId)
					.userId(userId)
					.build();
		
		return likeRepository.save(like);
	}
	
	// 게시글에 대응되는 좋아요 개수조회
	public int getLikeCount(int postId) {
		
		return likeRepository.countByPostId(postId);
		
	}
	
	// 특정 userId 와 postId가 일치하는 행 조회
	// 특정 사용자가 특정게시글에 좋아요를 했는지 안했는지
	public boolean isLikeByUserIdAndPostId(int userId, int postId){
		int count = likeRepository.countByUserIdAndPostId(userId, postId);
		
		if (count != 0) {
			return true;
		}else {
			return false;
		}
	}
}
