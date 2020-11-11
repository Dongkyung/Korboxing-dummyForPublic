package com.box.korBoxing.dao;

import org.springframework.stereotype.Repository;

import com.box.korBoxing.vo.InquiryVO;

@Repository
public interface InquiryDAO {

	/**
	 * 문의사항 등록
	 * 
	 * @param inquiryVO
	 * @return
	 */
	int registerInquiry(InquiryVO inquiryVO);
}
