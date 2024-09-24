package com.nowjoo.nowgram.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowgram.comment.domain.Comment;
import com.nowjoo.nowgram.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/nowgram/post")
public class CommentRestController {
	
	private CommentService commemtService;
	
	public CommentRestController(CommentService commemtService) {
		this.commemtService = commemtService;
	}
	
	// 댓글 작성
		@PostMapping("/comment/create")
		public Map<String, String> createComment(
				@RequestParam("postId") int postId
				, @RequestParam("comments") String comments
				, HttpSession session){
			
			int userId = (Integer)session.getAttribute("userId");
			
			Comment comment = commemtService.addComent(postId, userId, comments);
			
			Map<String, String> result = new HashMap<>();
			
			if(comment != null) {
				result.put("result", "success");
			}else {
				result.put("result", "fail");
			}
			return result;
		}
}
