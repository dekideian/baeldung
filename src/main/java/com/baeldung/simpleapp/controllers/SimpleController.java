package com.baeldung.simpleapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.baeldung.simpleapp.services.ConfigurationService;

@Controller
public class SimpleController {

	private final ConfigurationService configurationService;

	@Autowired
	public SimpleController(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@GetMapping("/")
	public String homePage(Model model) {

		model.addAttribute("appname", configurationService.getAppName());
		return "home";
	}
	
	
}
