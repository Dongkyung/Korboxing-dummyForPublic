package com.box.korBoxing.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.box.korBoxing.dao.bot.BoxerDAO_bot;
import com.box.korBoxing.vo.bot.BoxerInfoVO_bot;
import com.box.korBoxing.vo.bot.BoxerRankVO_bot;

/**
 * Boxer 서비스
 * @date : 2019. 5. 6. 
 * @author dklee
 * @discription
 *
 */

@Service
public class BotService {

	@Autowired
	private BoxerDAO_bot boxerDao_bot;
	
	/**
	 * Boxer 리스트 가져오기
	 */
	public String getBoxerRank_bot(String division_kor) {
		List<BoxerRankVO_bot> boxerRankList_bot = Collections.emptyList();
		boxerRankList_bot = boxerDao_bot.getActiveBoxerRank_bot(division_kor);
		StringBuffer sbf = new StringBuffer();
		sbf.append("first");
		sbf.append("["+division_kor+" 국내순위]");
		if(!boxerRankList_bot.isEmpty()) {
			sbf.append("--기준날짜:"+boxerRankList_bot.get(0).getDate());	
		}
		for(BoxerRankVO_bot item :boxerRankList_bot) {
			String itemStr = "--"+item.getRanking()+ " : " + item.getBoxer_name_kor();
			sbf.append(itemStr);
		}
		sbf.append("end");
		return sbf.toString();
		
	}
	
	
	public String getBoxerInfo_bot(String boxerName_kor) {
		List<BoxerInfoVO_bot> boxerInfo_bot = Collections.emptyList();
		boxerInfo_bot = boxerDao_bot.getBoxerInfo_bot(boxerName_kor);

		StringBuffer sbf = new StringBuffer();
		sbf.append("first");
		for(BoxerInfoVO_bot item :boxerInfo_bot) {
			System.out.println(item.toString());
			if(!StringUtils.isNotBlank(item.getRanking())||"null".equals(item.getRanking())){
				item.setRanking("X");
			}else {
				item.setRanking(item.getRanking()+"위");
			}
			sbf.append("--"+"기준날짜 : " + item.getDate());
			sbf.append("--"+"ID : " + item.getBoxer_id());
			sbf.append("--"+"이름 : " + item.getBoxer_name_kor());
			sbf.append("--"+"체급 : " + item.getDivision_kor());
			sbf.append("--"+"랭킹 : " + item.getRanking());
			if(boxerName_kor.equals("이준수")) {
				sbf.append("--"+"전적 : " + "9999승 / 무패");
			}else {
				sbf.append("--"+"전적 : " + item.getWin()+"승 / "+item.getDraw()+"무 / "+item.getLose()+"패");
			}
			sbf.append("--"+"상태 : " + item.getStatus());
		}
		sbf.append("end");
		return sbf.toString();
	}
}
