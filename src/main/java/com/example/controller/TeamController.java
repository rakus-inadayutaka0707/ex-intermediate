package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

@Controller
@RequestMapping("/")
public class TeamController {

	@Autowired
	private TeamService service;

	/**
	 * 野球チーム一覧画面表示.
	 * 
	 * @param model 全ての野球チーム情報
	 * @return 野球チーム一覧画面へ
	 */
	@RequestMapping("/show-list")
	public String showList(Model model) {
		List<Team> teamList = service.findAll();
		model.addAttribute("teamList", teamList);
		return "baseball/index";
	}

	/**
	 * 野球チーム情報画面へ.
	 * 
	 * @param id    検索する野球チーム情報
	 * @param model 検索した野球チーム情報
	 * @return 野球チーム情報画面へ
	 */
	@RequestMapping("/show-detail")
	public String showDetail(Integer id, Model model) {
		Team team = service.load(id);
		model.addAttribute("team", team);
		return "baseball/detail";
	}
}
