package com.box.korBoxing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.korBoxing.consts.URLPath;
import com.box.korBoxing.service.BoxerService;

@Controller
public class GymController {

	@Autowired
	private BoxerService boxerService;
	
	@GetMapping(value=URLPath.GYM_GYMINFO)
	public String gymInfo(ModelMap model) {
		return URLPath.GYM_GYMINFO;
	}
	
}
