package com.nowjoo.nowgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public List<Comment> findByPostId(int postId);
}
