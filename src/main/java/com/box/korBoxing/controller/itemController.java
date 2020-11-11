package com.box.korBoxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.box.korBoxing.consts.URLPath;

@Controller
public class itemController {

	
	@GetMapping(value=URLPath.ITEM_GLOVE)
	public String itemGlove(ModelMap model) {
		return URLPath.ITEM_GLOVE;
	}
	
	@GetMapping(value=URLPath.ITEM_GEAR)
	public String itemGear(ModelMap model) {
		return URLPath.ITEM_GEAR;
	}
	
	@GetMapping(value=URLPath.ITEM_ETC)
	public String itemEtc(ModelMap model) {
		return URLPath.ITEM_ETC;
	}
	

}
