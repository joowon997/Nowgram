package com.nowjoo.nowgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowgram.post.Service.PostService;
import com.nowjoo.nowgram.post.domain.Post;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/nowgram/post")
public class PostRestController {

	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	// 게시물 삭제
	@DeleteMapping("/delete")
	public Map<String, String> delectPost(
			@RequestParam("postId") int postId
			, HttpSession session){

		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.deletePost(postId, userId)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
	// 게시물 작성
	@PostMapping("/create")
	public Map<String, String> creatPost(
			@RequestParam("contents") String contents
			, @RequestParam("imageFile") MultipartFile file
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Post post = postService.addPost(userId, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (post != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
