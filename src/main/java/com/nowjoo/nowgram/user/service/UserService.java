package com.nowjoo.nowgram.user.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.common.hash.HashingEncoder;
import com.nowjoo.nowgram.common.hash.MD5HashingEncoder;
import com.nowjoo.nowgram.user.DTO.FindDTO;
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
	
	public List<FindDTO> findUser(String name, LocalDate birthday, String phoneNumber) {
		
		return userRepository.findUser(name, birthday, phoneNumber);
	}
	
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
}
