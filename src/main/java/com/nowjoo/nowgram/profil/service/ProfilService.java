package com.nowjoo.nowgram.profil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowgram.common.FileManager;
import com.nowjoo.nowgram.post.Service.PostService;
import com.nowjoo.nowgram.post.domain.Post;
import com.nowjoo.nowgram.post.dto.ProfilPostDTO;
import com.nowjoo.nowgram.post.repository.PostRepository;
import com.nowjoo.nowgram.profil.domain.Profil;
import com.nowjoo.nowgram.profil.dto.ProfilDTO;
import com.nowjoo.nowgram.profil.repositroy.ProfilRepository;
import com.nowjoo.nowgram.user.domain.User;
import com.nowjoo.nowgram.user.service.UserService;

@Service
public class ProfilService {

	private ProfilRepository profilRepository;
	private PostService postService;
	private UserService userService;
	
	public ProfilService(
			ProfilRepository profilRepository
			,PostService postService
			, UserService userService) {
		this.profilRepository = profilRepository;
		this.postService = postService;
		this.userService = userService;
	}
	
	//프로필 추가 기능
		public Profil addProfil(int userId, String nickname, MultipartFile file) {
			
			String urlPath = FileManager.saveFile(userId, file);
		
			Profil profil = Profil.builder()
							.userId(userId)
							.nickname(nickname)
							.imagePath(urlPath)
							.build();
			
			return profilRepository.save(profil);
		}
		
		
		
		// 프로필 정보 조회
		public ProfilDTO getProfil(int loginUserId) {
			Optional<Profil> optionalProfil = profilRepository.findByUserId(loginUserId);
			Profil profil = optionalProfil.orElse(null);
			
			if(profil != null) {
			List<ProfilPostDTO> profilPostDTO = postService.getProfilPost(loginUserId);
			
			User user = userService.getUserById(loginUserId);
			
			int postCount = postService.getPostCount(loginUserId);
			
			boolean isProfil = isProfil(loginUserId);
			
			ProfilDTO profilDTO = ProfilDTO.builder()
									.userId(loginUserId)
									.profilId(profil.getId())
									.postList(profilPostDTO)
									.loginId(user.getLoginId())
									.nikename(profil.getNickname())
									.profilImage(profil.getImagePath())
									.postCount(postCount)
									.isProfil(isProfil)
									.build();
				return profilDTO;

			}else {
				List<ProfilPostDTO> profilPostDTO = postService.getProfilPost(loginUserId);
				
				User user = userService.getUserById(loginUserId);
				
				int postCount = postService.getPostCount(loginUserId);
				
				boolean isProfil = isProfil(loginUserId);
				
				ProfilDTO profilDTO = ProfilDTO.builder()
										.userId(loginUserId)
										.postList(profilPostDTO)
										.loginId(user.getLoginId())
										.postCount(postCount)
										.isProfil(isProfil)
										.build();
				
				return profilDTO;
			}
		}
		
		// 프로필 존재 조회
		public boolean isProfil(int userId) {
			
			int count = profilRepository.countByUserId(userId);
			
			if (count != 0) {
				return true;
			}else {
				return false;
			}
		}
}
