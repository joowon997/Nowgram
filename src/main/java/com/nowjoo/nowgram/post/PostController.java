package com.nowjoo.nowgram.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowgram/post")
public class PostController {

	@GetMapping("/timeline-view")
	public String timelineView() {
		return"post/timeline";
	}
	
	@GetMapping("/create-view")
	public String createView() {
		return"post/create";
	}
}
