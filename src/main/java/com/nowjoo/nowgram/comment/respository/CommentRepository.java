package com.nowjoo.nowgram.comment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	
}
