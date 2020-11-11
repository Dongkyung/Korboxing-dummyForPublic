package com.box.korBoxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.box.korBoxing.consts.URLPath;

@Controller
public class HomeController {
		
	@GetMapping(value=URLPath.ROOT)
	public String home(ModelMap model) {
		return URLPath.ROOT+"index";
	}

}
