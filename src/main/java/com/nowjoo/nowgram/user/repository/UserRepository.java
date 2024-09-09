package com.nowjoo.nowgram.user.repository;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("loginId") String loginId
			,@Param("password") String password
			,@Param("name") String name
			,@Param("birthday") LocalDate birthday
			,@Param("email") String email
			,@Param("phoneNumber") String phoneNumber
			);
	
	public int selectCountByLoginId(@Param("loginId") String loginId);
}
