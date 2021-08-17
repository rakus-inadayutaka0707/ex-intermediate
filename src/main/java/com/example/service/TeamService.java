package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Team;
import com.example.repository.TeamRepository;

/**
 * TeamRepositoryリポジトリのServiceクラス.
 * 
 * @author inada
 *
 */
@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository repository;

	/**
	 * 全チームを検索する.
	 * 
	 * @return 検索した全チーム情報
	 */
	public List<Team> findAll() {
		return repository.findAll();
	}

	/**
	 * 1チームの情報を検索する.
	 * 
	 * @param id 検索したいチームのID
	 * @return 検索したチーム情報
	 */
	public Team load(Integer id) {
		return repository.load(id);
	}
}
