package com.nowjoo.nowgram.user.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nowjoo.nowgram.user.DTO.FindDTO;
import com.nowjoo.nowgram.user.domain.User;

@Mapper
public interface UserRepository {
	// 아이디 찾기
	public List<FindDTO> findUser(
			@Param("name") String name
			, @Param("birthday")  LocalDate birthday
			, @Param("phoneNumber") String phoneNumber
			);
	
	
	public User selectUserById(
			@Param("id") int id);
	
	// 로그인
	public User selectUser(
			@Param("loginId") String loginId
			,@Param("password") String password);

	// 프로필 설정
	public int insertProfil(
			@Param("userId") int userId
			, @Param("nickname") String nickname
			, @Param("imagePath") String imagePath
			);
	
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
