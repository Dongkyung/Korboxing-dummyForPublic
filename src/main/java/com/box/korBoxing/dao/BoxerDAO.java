package com.box.korBoxing.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.box.korBoxing.vo.BoutDetailVO;
import com.box.korBoxing.vo.BoxerDetailVO;
import com.box.korBoxing.vo.BoxerListVO;
import com.box.korBoxing.vo.PagingVO;

@Repository
public interface BoxerDAO {
	
	int getboxerTotalCount(PagingVO pagingVO);

	int getActiveBoxerCount(PagingVO pagingVO);
	
	int getInactiveBoxerCount(PagingVO pagingVO);
	
	List<BoxerListVO> getActiveBoxerList(@Param("division") String division, @Param("sex") String sex);
	
	List<BoxerListVO> getInactiveBoxerList(@Param("division") String division, @Param("sex") String sex);
	
	List<BoxerListVO> getBoxerListForTranslate(String division);
	
	BoxerDetailVO getBoxerDetail(String boxerId);
	List<BoutDetailVO> getBoutDetail(String boxerId);
	
	int insertTranslate(HashMap<String,String> paramMap);
}
