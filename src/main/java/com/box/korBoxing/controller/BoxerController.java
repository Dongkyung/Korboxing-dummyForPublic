package com.box.korBoxing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.korBoxing.consts.URLPath;
import com.box.korBoxing.service.BoxerService;
import com.box.korBoxing.vo.BoutDetailVO;
import com.box.korBoxing.vo.BoxerDetailVO;
import com.box.korBoxing.vo.PagingVO;
import com.box.korBoxing.vo.common.ObjectJsonResponseVO;

@Controller
public class BoxerController {

	@Autowired
	private BoxerService boxerService;
	
	
	
	@GetMapping(value=URLPath.BOXER_PROBOXER_HOF)
	public String boxerProboxerHof(ModelMap model) {
		return URLPath.BOXER_PROBOXER_HOF;
	}
	
	@GetMapping(value=URLPath.BOXER_PROBOXER_MBOXERINFO)
	public String boxerProboxerMboxerinfo(String division, String status, ModelMap model) {
		if(division == null || division == "" ) {
			division = "choice";
		}
		if(status == null || status == "") {
			status = "active";
		}
		boxerService.getBoxerList(division,"M",status, model);
		return URLPath.BOXER_PROBOXER_MBOXERINFO;
	}
	
	@GetMapping(value=URLPath.BOXER_PROBOXER_FBOXERINFO)
	public String boxerProboxerFboxerinfo(String division, String status, ModelMap model) {
		if(division == null || division == "" ) {
			division = "choice";
		}
		if(status == null || status == "") {
			status = "active";
		}
		boxerService.getBoxerList(division,"F",status, model);
		return URLPath.BOXER_PROBOXER_FBOXERINFO;
	}
	
	
	
	
	
	@GetMapping(value=URLPath.BOXER_AMABOXER_HOF)
	public String boxerAmaboxerHof(ModelMap model) {
		return URLPath.BOXER_AMABOXER_HOF;
	}
	
	@GetMapping(value=URLPath.BOXER_AMABOXER_MBOXERINFO)
	public String boxerAmaboxerMboxerinfo(ModelMap model) {
		return URLPath.BOXER_AMABOXER_MBOXERINFO;
	}
	
	@GetMapping(value=URLPath.BOXER_AMABOXER_FBOXERINFO)
	public String boxerAmaboxerFboxerinfo(ModelMap model) {
		return URLPath.BOXER_AMABOXER_FBOXERINFO;
	}
	
	
	
	
	
	
	
	
	@ResponseBody
	@GetMapping(value=URLPath.BOXER_BOXERDETAIL ,produces = "application/json; charset=utf8")
	public String  BoxerDetail(String boxerId, ModelMap model) {
        BoxerDetailVO result = boxerService.getBoxerDetail(boxerId);
        int statusCode = result == null? 0:1;
		return new ObjectJsonResponseVO(statusCode, result).toString();
	}
	
	@ResponseBody
	@GetMapping(value=URLPath.BOXER_BOUTDETAIL ,produces = "application/json; charset=utf8")
	public String  BoutDetail(String boxerId, ModelMap model) {
        List<BoutDetailVO> result = boxerService.getBoutDetail(boxerId);
        int statusCode = result.toString().length() == 0? 0:1;
		return new ObjectJsonResponseVO(statusCode, result).toString();
	}
	
	
	
	@GetMapping(value= URLPath.BOXERTRANSLATE)
	public String BoxerListForTranslate(String division,ModelMap model) {
		boxerService.getBoxerListForTranslate(division, model);
		return URLPath.BOXERTRANSLATE;
	}
	
	@ResponseBody
	@PostMapping(value= URLPath.SUPPORTTRANSLATE)
	public String SupportTranslate(String boxerId,String boxerNameKor, String boxerNameEng, String mode) {
		String msg = boxerService.insertTranslate(boxerId, boxerNameKor, boxerNameEng, mode);
		return msg;
	}
	
	
	@GetMapping(value= URLPath.TEMPBOXERLiST)
	public String MboxerInfo(PagingVO pagingVO, ModelMap model) {
		boxerService.getBoxerList(pagingVO.getDivision(),"M", pagingVO.getStatus(), model);
		return URLPath.TEMPBOXERLiST;
	}
	
	
}
