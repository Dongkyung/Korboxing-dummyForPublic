package com.box.korBoxing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.korBoxing.consts.URLPath;
import com.box.korBoxing.service.BotService;
import com.box.korBoxing.vo.PagingVO;

@Controller
public class BotController {

	@Autowired
	private BotService botService;
	
	
	@ResponseBody
	@GetMapping(value= URLPath.BOT_BOXERRANK, produces = "application/json; charset=utf8")
	public String Bot_BoxerRank(String division_kor) {
		String result = botService.getBoxerRank_bot(division_kor);
		return result;
	}
	
	@ResponseBody
	@GetMapping(value= URLPath.BOT_BOXERINFO, produces = "application/json; charset=utf8")
	public String Bot_BoxerInfo(String boxerName_kor) {
		String result = botService.getBoxerInfo_bot(boxerName_kor);
		return result;
	}
	
//	@GetMapping(value="/boxerList")
//	public String other(ModelMap model) {
//		model.addAttribute("fightStr", "abcBoxing");
//		return "/boxerList";
//	}
}
