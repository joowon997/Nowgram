package com.nowjoo.nowgram.post.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowgram.comment.domain.Comment;
import com.nowjoo.nowgram.comment.dto.CommentView;
import com.nowjoo.nowgram.comment.service.CommentService;
import com.nowjoo.nowgram.common.FileManager;
import com.nowjoo.nowgram.like.service.LikeService;
import com.nowjoo.nowgram.post.domain.Post;
import com.nowjoo.nowgram.post.dto.ProfilPostDTO;
import com.nowjoo.nowgram.post.dto.TimelineDto;
import com.nowjoo.nowgram.post.repository.PostRepository;
import com.nowjoo.nowgram.user.domain.User;
import com.nowjoo.nowgram.user.service.UserService;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	public PostService(
			PostRepository postRepository
			, UserService userService
			, LikeService likeService
			, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService =likeService;
		this.commentService=commentService;
	}
	
	// 타임라인
	public List<TimelineDto> getTimelineList(int loginUserId){
		
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<TimelineDto> timelineDtoList = new ArrayList<>();
		for(Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			int likeCount = likeService.getLikeCount(post.getId());
			boolean islike = likeService.isLikeByUserIdAndPostId(loginUserId, post.getId());
		
			List<CommentView> commentList = commentService.getCommentListByPostId(post.getId());
			
			TimelineDto timelineView = TimelineDto.builder()
										.postId(post.getId())
										.userId(userId)
										.contents(post.getContents())
										.imagePath(post.getImagePath())
										.loginId(user.getLoginId())
										.likeCount(likeCount)
										.isLike(islike)
										.commentList(commentList)
										.build();

			timelineDtoList.add(timelineView);
		}
		
		return timelineDtoList;
	}
	
	// 게시물 추가
	public Post addPost(int userId, String contents, MultipartFile file) {
		
		String urlPath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
					.userId(userId)
					.contents(contents)
					.imagePath(urlPath)
					.build();
		
		Post result = postRepository.save(post);
		
		return result;
	}
	
	// 메모 삭제
	public boolean deletePost(int postId, int userId) {
		
		Optional<Post> optionalPost = postRepository.findByIdAndUserId(postId, userId);
		Post post = optionalPost.orElse(null);
		
		
		if (post != null) {
			// 좋아요 삭제
			likeService.deleteLikeByPostId(postId);
			// 댓글 삭제
			commentService.deleteCommentByPostId(postId);
			
			FileManager.removeFile(post.getImagePath());
			postRepository.delete(post);
			return true;
		}else {
			return false;
		}
	
	}
	
	// 프로필 정보 전달
	public List<ProfilPostDTO> getProfilPost(int userId) {
		List<Post> postList = postRepository.findByUserId(userId);
		
		List<ProfilPostDTO> profilPostDTOList = new ArrayList<>();
		for(Post post:postList) {
			ProfilPostDTO profilPostDTO = ProfilPostDTO.builder()
											.postId(post.getId())
											.postImage(post.getImagePath())
											.build();
			profilPostDTOList.add(profilPostDTO);
		}
		return profilPostDTOList;
	}
	
	public int getPostCount(int userId) {
		
		return postRepository.countByUserId(userId);
	}
	
}
