package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;

/**
 * TeamドメインのRepositoryクラス.
 * 
 * @author inada
 *
 */
@Repository
public class TeamRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLENAME = "teams";

	private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};

	/**
	 * 全チームを検索する.
	 * 
	 * @return 検索した全チーム情報
	 */
	public List<Team> findAll() {
		String sql = "select id,league_name,team_name,headquarters,inauguration,history from " + TABLENAME
				+ " order by id asc";
		List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);
		return teamList;
	}

	/**
	 * 1チームの情報を検索する.
	 * 
	 * @param id 検索したいチームのID
	 * @return 検索したチーム情報
	 */
	public Team load(Integer id) {
		String sql = "select id,league_name,team_name,headquarters,inauguration,history from " + TABLENAME + " where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
		return team;
	}
}
