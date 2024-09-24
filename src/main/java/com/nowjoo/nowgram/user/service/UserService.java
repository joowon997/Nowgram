package com.nowjoo.nowgram.user.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowgram.common.FileManager;
import com.nowjoo.nowgram.common.hash.HashingEncoder;
import com.nowjoo.nowgram.user.domain.Profil;
import com.nowjoo.nowgram.user.domain.User;
import com.nowjoo.nowgram.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	private HashingEncoder encoder;
	
	// IoC : 제어의 역전
	// DI(Dependency injection) : 의존성 주입 
	public UserService(UserRepository userRepository, @Qualifier("sha256Hashing") HashingEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	//프로필 추가 기능
	public int addProfil(int userId, String nickname, MultipartFile file) {
		
		String urlPath = FileManager.saveFile(userId, file);
	
		return userRepository.insertProfil(userId, nickname, urlPath);
	}
	
	// 프로필 조회
	public Profil getProfil(int userId) {
		return userRepository.selectProfilByUserId(userId);
	}
	
	// 로그인 기능
	public User getUser(String loginId, String password) {

		String encryptPassword = encoder.encode(password);
		
		return userRepository.selectUser(loginId, encryptPassword);
		
	}
	
	
	// 회원가입 기능
	public int adduser(
			String loginId
			,String password
			,String name
			,LocalDate birthday
			,String email
			,String phoneNumber
			) {
		
		String encryptPassword = encoder.encode(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, birthday, email, phoneNumber);
	}
	
	// 중복확인 기능
	public boolean isDuplicateId(String loginId){
		int count = userRepository.selectCountByLoginId(loginId);
		
		if (count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public User getUserById(int id) {
		return userRepository.selectUserById(id);
	}
}
