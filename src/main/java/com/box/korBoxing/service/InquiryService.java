package com.box.korBoxing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.box.korBoxing.dao.InquiryDAO;
import com.box.korBoxing.vo.InquiryVO;

/**
 * 문의사항 서비스
 * 
 * @author dklee
 *
 */

@Service
public class InquiryService {

	@Autowired
	private InquiryDAO inquiryDao;
	
	/**
	 * 문의사항 등록
	 * 
	 * @param inquiryVO
	 * @return
	 */
	
	public int registerInquiry(InquiryVO inquiryVO) {
		return inquiryDao.registerInquiry(inquiryVO);
	}


}
