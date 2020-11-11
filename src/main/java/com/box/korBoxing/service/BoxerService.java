package com.box.korBoxing.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.box.korBoxing.consts.Translation;
import com.box.korBoxing.dao.BoxerDAO;
import com.box.korBoxing.vo.BoutDetailVO;
import com.box.korBoxing.vo.BoxerDetailVO;
import com.box.korBoxing.vo.BoxerListVO;

/**
 * Boxer 서비스
 * @date : 2019. 5. 6. 
 * @author dklee
 * @discription
 *
 */

@Service
public class BoxerService {

	@Autowired
	private BoxerDAO boxerDao;
	
	/**
	 * Boxer 리스트 가져오기
	 */
	public void getBoxerList(String division, String sex, String status, ModelMap model) {
		
		String division_param = division.replace("_", " "); 
		List<BoxerListVO> boxers = Collections.emptyList();
		
		
		if(status.equals("active")) {
			boxers = boxerDao.getActiveBoxerList(division_param, sex);
		}else if(status.equals("inactive")) {
			boxers = boxerDao.getInactiveBoxerList(division_param, sex);
		}
		
		model.addAttribute("division_kor", Translation.TRANS_DIVISION_MAP.get(division_param));
		model.addAttribute("status", status);
		
		if(!division.equals("choice")) System.out.println(new Date().toString()+Translation.TRANS_DIVISION_MAP.get(division_param)+" 조회");
		model.addAttribute("boxerList",boxers);
	}
	
	public BoxerDetailVO getBoxerDetail(String boxerId) {
		BoxerDetailVO boxerDetailVO = new BoxerDetailVO();
		boxerDetailVO = boxerDao.getBoxerDetail(boxerId);
		if(boxerDetailVO == null) {
			return null;
		}
		boxerDetailVO.setStatus(Translation.TRANS_STATUS_MAP.get(boxerDetailVO.getStatus()));
		boxerDetailVO.setStance(Translation.TRANS_STANCE_MAP.get(boxerDetailVO.getStance()));
		return boxerDetailVO;
	}
	
	public List<BoutDetailVO> getBoutDetail(String boxerId) {
		List<BoutDetailVO> boutDetailList = new ArrayList<BoutDetailVO>();
		boutDetailList = boxerDao.getBoutDetail(boxerId);
//		boutDetailVO.setStatus(Translation.TRANS_STATUS_MAP.get(boxerDetailVO.getStatus()));
//		boutDetailVO.setStance(Translation.TRANS_STANCE_MAP.get(boxerDetailVO.getStance()));
		return boutDetailList;
	}
	
	
	
	
	public void getBoxerListForTranslate(String division, ModelMap model) {
		String division_param = division.replace("_", " "); 
		List<BoxerListVO> boxers = Collections.emptyList();
		boxers = boxerDao.getBoxerListForTranslate(division_param);
		if(boxers.size() != 0) {
			model.addAttribute("division_kor", boxers.get(0).getDivision());
		}
		System.out.println(new Date().toString()+boxers.get(0).getDivision()+" 체급 조회");
		model.addAttribute("boxerList",boxers);
	}
	
	
	
	
	public String insertTranslate(String boxerId, String boxerNameKor, String boxerNameEng, String mode) {
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("boxerId", boxerId);
		paramMap.put("boxerNameKor", boxerNameKor);
		paramMap.put("boxerNameEng", boxerNameEng);
		paramMap.put("mode", mode);
		int result = boxerDao.insertTranslate(paramMap);
		String msg;
        switch (result) {
            case 0:
                msg = "요청에 실패했습니다.";
                break;
            case 1:
                msg = "등록 완료하였습니다.";
                break;
            default: msg = "알수없는 오류입니다.";
        }
        return msg;
		
	}
	
	
}
	
	
	

