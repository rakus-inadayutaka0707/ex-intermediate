package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Hotel;
import com.example.form.HotelInputPriceForm;
import com.example.service.HotelService;

/**
 * ホテル情報操作を行うControllerクラス.
 * 
 * @author inada
 *
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService service;

	@ModelAttribute
	private HotelInputPriceForm setUpHotelInputPriceForm() {
		return new HotelInputPriceForm();
	}

	/**
	 * ホテル検索画面を表示する.
	 * 
	 * @return ホテル検索画面を表示
	 */
	@RequestMapping("/top")
	public String index() {
		return "hotel/index";
	}

	/**
	 * ホテル検索を行う.
	 * 
	 * @param form               検索したいホテルの金額
	 * @param redirectAttributes 検索したホテル情報
	 * @return ホテル検索画面へリダイレクト
	 */
	@RequestMapping("/search")
	public String search(HotelInputPriceForm form, RedirectAttributes redirectAttributes) {
		if(form.getPrice().equals("")) {
			form.setPrice(null);
		}
		List<Hotel> hotelList = service.searchByLessThanPrice(Integer.parseInt(form.getPrice()));
		redirectAttributes.addFlashAttribute("hotelList", hotelList);
		return "redirect:/hotel/top";
	}
}
