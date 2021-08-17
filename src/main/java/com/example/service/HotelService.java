package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Hotel;
import com.example.repository.HotelRepository;

/**
 * ホテル情報を操作するサービスクラス.
 * 
 * @author inada
 *
 */
@Service
@Transactional
public class HotelService {

	@Autowired
	private HotelRepository repository;

	/**
	 * 入力金額より安いホテルを検索する.
	 * 
	 * @param price 入力金額
	 * @return 該当したホテルの情報
	 */
	public List<Hotel> searchByLessThanPrice(Integer price) {
		if(price == null) {
			return repository.findAll();
		}
		return repository.findByPrice(price);
	}
}
