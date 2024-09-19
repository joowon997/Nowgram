package com.nowjoo.nowgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowgram.post.Service.PostService;
import com.nowjoo.nowgram.post.dto.TimelineDto;

@Controller
@RequestMapping("/nowgram/post")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/timeline-view")
	public String timelineView(
		Model model) {
		
		List<TimelineDto> timelineList = postService.getTimelineList();
		
		model.addAttribute("timeline", timelineList);
		
		
		return"post/timeline";
	}
	
	@GetMapping("/create-view")
	public String createView() {
		return"post/create";
	}
}
