package com.nowjoo.nowgram.user.repository;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nowjoo.nowgram.user.domain.User;

@Mapper
public interface UserRepository {
	// 로그인
	public User selectUser(
			@Param("loginId") String loginId
			,@Param("password") String password);
	
	// 회원가입
	public int insertUser(
			@Param("loginId") String loginId
			,@Param("password") String password
			,@Param("name") String name
			,@Param("birthday") LocalDate birthday
			,@Param("email") String email
			,@Param("phoneNumber") String phoneNumber
			);
	
	// 중복확인
	public int selectCountByLoginId(@Param("loginId") String loginId);


}
