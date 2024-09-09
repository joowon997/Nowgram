package com.nowjoo.nowgram.user.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nowjoo.nowgram.common.hash.HashingEncoder;
import com.nowjoo.nowgram.common.hash.MD5HashingEncoder;
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
	
	public boolean isDuplicateId(String loginId){
		int count = userRepository.selectCountByLoginId(loginId);
		
		if (count == 0) {
			return false;
		}else {
			return true;
		}
	}
}
