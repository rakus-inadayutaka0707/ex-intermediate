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

	@RequestMapping("/show-list")
	public String showList(Model model) {
		List<Team> teamList = service.findAll();
		model.addAttribute("teamList", teamList);
		return "baseball/index";
	}

	@RequestMapping("/show-detail")
	public String showDetail(Integer id, Model model) {
		Team team = service.load(id);
		model.addAttribute("team", team);
		return "baseball/detail";
	}
}
