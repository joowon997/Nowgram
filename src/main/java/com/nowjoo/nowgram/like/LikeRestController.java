package com.nowjoo.nowgram.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowgram.like.domain.Like;
import com.nowjoo.nowgram.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/nowgram/post")
public class LikeRestController {

	private LikeService likeService;
	
	public LikeRestController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@RequestMapping("/like")
	public Map<String, String> addLike(
			@RequestParam("postId") int postId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Like like = likeService.addLike(postId, userId);
		
		Map<String, String> result = new HashMap<>();
		
		if (like != null) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
}
