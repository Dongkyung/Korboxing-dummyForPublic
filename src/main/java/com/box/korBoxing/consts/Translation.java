package com.box.korBoxing.consts;

import java.util.HashMap;
import java.util.Map;

public class Translation {
	
	public static Map<String,String> TRANS_STATUS_MAP = new HashMap<String,String>();
	public static Map<String,String> TRANS_STANCE_MAP = new HashMap<String,String>();
	public static Map<String,String> TRANS_DIVISION_MAP = new HashMap<String,String>();
	
	static {
		TRANS_STATUS_MAP.put("active", "현역");
		TRANS_STATUS_MAP.put("inactive", "은퇴");
		
		TRANS_STANCE_MAP.put("orthodox","오소독스");
		TRANS_STANCE_MAP.put("southpaw","사우스포");
		
		TRANS_DIVISION_MAP.put("choice","선택");
		TRANS_DIVISION_MAP.put("all","전체");
		TRANS_DIVISION_MAP.put("bantamweight","벤텀급");
		TRANS_DIVISION_MAP.put("cruiserweight","크루저급");
		TRANS_DIVISION_MAP.put("featherweight","패더급");
		TRANS_DIVISION_MAP.put("flyweight","플라이급");
		TRANS_DIVISION_MAP.put("heavyweight","헤비급");
		TRANS_DIVISION_MAP.put("light flyweight","라이트플라이급");
		TRANS_DIVISION_MAP.put("light heavyweight","라이트헤비급");
		TRANS_DIVISION_MAP.put("light middleweight","슈퍼웰터급");
		TRANS_DIVISION_MAP.put("light welterweight","슈퍼라이트급");
		TRANS_DIVISION_MAP.put("lightweight","라이트급");
		TRANS_DIVISION_MAP.put("middleweight","미들급");
		TRANS_DIVISION_MAP.put("minimumweight","미니멈급");
		TRANS_DIVISION_MAP.put("super bantamweight","슈퍼벤텀급");
		TRANS_DIVISION_MAP.put("super featherweight","슈퍼패더급");
		TRANS_DIVISION_MAP.put("super flyweight","슈퍼플라이급");
		TRANS_DIVISION_MAP.put("super middleweight","슈퍼미들급");
		TRANS_DIVISION_MAP.put("welterweight","웰터급");
	}
}
