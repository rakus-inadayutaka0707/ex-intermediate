package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Clothe;
import com.example.service.ClotheService;

/**
 * 衣類検索を行う操作をするためのクラス.
 * 
 * @author inada
 *
 */
@Controller
@RequestMapping("/clothe")
public class ClotheController {

	@Autowired
	private ClotheService service;

	/**
	 * 衣類検索画面を表示する
	 * 
	 * @param model 服の色を表示するための情報
	 * @return 衣類検索画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		Map<String, String> colorList = service.searchByAllColor();
		model.addAttribute("colorList", colorList);
		return "clothe/index";
	}

	/**
	 * 入力された条件で衣類を検索する.
	 * 
	 * @param gender             性別情報
	 * @param color              色情報
	 * @param redirectAttributes 検索した服の情報
	 * @return 衣類検索画面を表示する
	 */
	@RequestMapping("/search")
	public String search(Integer gender, String color, RedirectAttributes redirectAttributes) {
		List<Clothe> clothes = service.searchByColorAndGender(gender, color);
		redirectAttributes.addFlashAttribute("clothes", clothes);
		return "redirect:/clothe";
	}
}
