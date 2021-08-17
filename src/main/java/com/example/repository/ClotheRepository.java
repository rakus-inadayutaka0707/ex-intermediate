package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Clothe;

/**
 * clothesテーブルでSQL操作を行うためのRepositoryクラス.
 * 
 * @author inada
 *
 */
@Repository
public class ClotheRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABELNAME = "clothes";

	private static final RowMapper<Clothe> CLOTHE_ROW_MAPPER = (rs, i) -> {
		Clothe clothe = new Clothe();
		clothe.setId(rs.getInt("id"));
		clothe.setCategory(rs.getString("category"));
		clothe.setGenre(rs.getString("genre"));
		clothe.setGender(rs.getInt("gender"));
		clothe.setColor(rs.getString("color"));
		clothe.setPrice(rs.getInt("price"));
		clothe.setSize(rs.getString("size"));
		return clothe;
	};

	/**
	 * 性別と色から服を検索する.
	 * 
	 * @param gender 性別情報
	 * @param color  色情報
	 * @return 検索した結果の情報
	 */
	public List<Clothe> findByGenderAndColor(Integer gender, String color) {
		String sql = "select id,category,genre,gender,color,price,size from " + TABELNAME
				+ " where gender=:gender and color=:color order by price asc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("gender", gender).addValue("color", color);
		List<Clothe> clotheList = template.query(sql, param, CLOTHE_ROW_MAPPER);
		return clotheList;
	}

	/**
	 * 登録している服を全件検索する.
	 * 
	 * @return 検索した服の情報
	 */
	public List<Clothe> findAll() {
		String sql = "select id,category,genre,gender,color,price,size from " + TABELNAME + " order by price asc;";
		List<Clothe> clotheList = template.query(sql, CLOTHE_ROW_MAPPER);
		return clotheList;
	}
}
