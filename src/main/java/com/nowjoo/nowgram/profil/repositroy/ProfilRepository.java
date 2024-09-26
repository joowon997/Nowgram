package com.nowjoo.nowgram.profil.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowgram.profil.domain.Profil;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {

	public int countByUserId(int userId);
	
	public Optional<Profil> findByUserId(int userId);
}
