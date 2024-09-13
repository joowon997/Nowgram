package com.nowjoo.nowgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PsotRestController {

	@PostMapping("/creat")
	public Map<String, String> creatPost(
			@RequestParam("contents") String contents
			, @RequestParam("imageFile") MultipartFile file){
		
		Map<String, String> resultMap = new HashMap<>();
		
		return resultMap;
	}
}
