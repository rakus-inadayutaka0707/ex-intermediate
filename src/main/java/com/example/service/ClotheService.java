package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Clothe;
import com.example.repository.ClotheRepository;

/**
 * 服情報を操作するためのサービスクラス.
 * 
 * @author inada
 *
 */
@Service
@Transactional
public class ClotheService {

	@Autowired
	private ClotheRepository repository;

	/**
	 * 性別と色から服を検索する.
	 * 
	 * @param gender 性別情報
	 * @param color  色情報
	 * @return 検索した結果の情報
	 */
	public List<Clothe> searchByColorAndGender(Integer gender, String color) {
		return repository.findByGenderAndColor(gender, color);
	}

	/**
	 * 登録している服の色を取得する.<br>
	 * ArrayListで検索した結果で重複をなくすためにMapに入れなおす処理をしています。
	 * 検索時に重複をなくす
	 * 
	 * @return 検索した服の情報
	 */
	public Map<String, String> searchByAllColor() {
		Map<String, String> colorMap = new HashMap<>();
		List<Clothe> clotheList = repository.findAll();
		for (Clothe clothe : clotheList) {
			colorMap.put(clothe.getColor(), clothe.getColor());
		}
		return colorMap;
	}
}
