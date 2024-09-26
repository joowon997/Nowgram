package com.nowjoo.nowgram.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowgram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	public List<Post> findAllByOrderByIdDesc();
	
	public Optional<Post> findByIdAndUserId(int postId, int userId);
	
	public List<Post> findByUserId(int userId);
	
	public int countByUserId(int userId);
}
