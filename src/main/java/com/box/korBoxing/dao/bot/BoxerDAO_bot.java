package com.box.korBoxing.dao.bot;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.box.korBoxing.vo.bot.BoxerInfoVO_bot;
import com.box.korBoxing.vo.bot.BoxerRankVO_bot;

@Repository
public interface BoxerDAO_bot {
	
	List<BoxerRankVO_bot> getActiveBoxerRank_bot(String division_kor);
	
	List<BoxerInfoVO_bot> getBoxerInfo_bot(String boxerName_kor);
}
