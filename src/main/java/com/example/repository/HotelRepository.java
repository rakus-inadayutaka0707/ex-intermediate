package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

/**
 * hotelsテーブルのSQL操作を行うクラス.
 * 
 * @author inada
 *
 */
@Repository
public class HotelRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLENAME = "hotels";

	private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
		Hotel hotel = new Hotel();
		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));
		return hotel;
	};

	/**
	 * 入力金額より安いホテルを検索する.
	 * 
	 * @param price 入力金額
	 * @return 該当したホテルの情報
	 */
	public List<Hotel> findByPrice(Integer price) {
		String sql = "select id,area_name,hotel_name,address,nearest_station,price,parking from " + TABLENAME
				+ " where price <= :price order by price asc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
		List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);
		return hotelList;
	}

	/**
	 * ホテルを全件検索する.
	 * 
	 * @return 検索したホテルの情報
	 */
	public List<Hotel> findAll() {
		String sql = "select id,area_name,hotel_name,address,nearest_station,price,parking from " + TABLENAME
				+ " order by price asc;";
		List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
		return hotelList;
	}
}
