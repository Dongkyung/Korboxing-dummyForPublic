package com.box.korBoxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.box.korBoxing.consts.URLPath;

@Controller
public class OrganizaionController {

	@GetMapping(value=URLPath.ORGANIZATION_ORGANKOR_INFO)
	public String organizationOrgankorInfo(ModelMap model) {
		return URLPath.ORGANIZATION_ORGANKOR_INFO;
	}
	
	@GetMapping(value=URLPath.ORGANIZATION_ORGANKOR_CHAMP)
	public String organizationOrgankorChamp(ModelMap model) {
		return URLPath.ORGANIZATION_ORGANKOR_CHAMP;
	}
	
}
