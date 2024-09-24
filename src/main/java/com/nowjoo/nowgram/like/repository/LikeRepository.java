package com.nowjoo.nowgram.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{

}
