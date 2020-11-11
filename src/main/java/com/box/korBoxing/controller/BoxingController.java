package com.box.korBoxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.box.korBoxing.consts.URLPath;

@Controller
public class BoxingController {

	
	@GetMapping(value=URLPath.BOXING_PROBOX_INFO)
	public String boxingProboxerInfo(ModelMap model) {
		return URLPath.BOXING_PROBOX_INFO;
	}
	
	@GetMapping(value=URLPath.BOXING_PROBOX_WAY)
	public String boxingProboxWay(ModelMap model) {
		return URLPath.BOXING_PROBOX_WAY;
	}
	
	
	
	
	@GetMapping(value=URLPath.BOXING_ARMABOX_INFO)
	public String boxingAmaboxInfo(ModelMap model) {
		return URLPath.BOXING_ARMABOX_INFO;
	}
	
	@GetMapping(value=URLPath.BOXING_ARMABOX_WAY)
	public String boxingAmaboxWay(ModelMap model) {
		return URLPath.BOXING_ARMABOX_WAY;
	}
	
}
