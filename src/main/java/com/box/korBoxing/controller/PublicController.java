package com.box.korBoxing.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.korBoxing.service.InquiryService;
import com.box.korBoxing.util.Util;
import com.box.korBoxing.vo.InquiryVO;

@Controller
public class PublicController {

	@Autowired
	private Util util;
	@Autowired
	private InquiryService inquiryService;
	
	
	@RequestMapping(value = "/ads.txt")
	@ResponseBody
	public String adstxt(HttpServletResponse response) {
	     String fileName = "ads.txt";
	     response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	     String content = "google.com, pub-8631659850210196, DIRECT, f08c47fec0942fa0";
	     return content;
	}
	
	/**
	 * 문의하기
	 * 
	 * @param inquiryVO
	 * @return
	 */
	@PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String inquiry(@Valid InquiryVO inquiryVO, BindingResult bindingResult) {
		String response = util.createErrorMessage(null);
		if (bindingResult.hasErrors()) {
			response = util.createErrorMessage(bindingResult.getFieldErrors());
			return response;
		} else {			
			final String newLine = "\n";
			StringBuilder mailBody = new StringBuilder();
			mailBody.append("닉네임 : ").append(inquiryVO.getNickName()).append(newLine)
					.append("이름 : ").append(inquiryVO.getUserName()).append(newLine)
					.append("연락처 : ").append(inquiryVO.getPhoneNumber()).append(newLine)
					.append("이메일 : ").append(inquiryVO.getEmail()).append(newLine)
					.append("문의내용 : \n").append(inquiryVO.getContent());
			if(util.sendMail(inquiryVO.getDestinationEmail(), "BoxWorld - "+ inquiryVO.getCategory(), mailBody.toString()) &&
				0 < inquiryService.registerInquiry(inquiryVO)) {
					response = util.createSuccessMessage();
			}
		}
		
		return response;
	}
}
