package com.box.korBoxing.vo.bot;

public class BoxerRankVO_bot {
	private String date;
	private String ranking;
	private String boxer_name_kor;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getBoxer_name_kor() {
		return boxer_name_kor;
	}
	public void setBoxer_name_kor(String boxer_name_kor) {
		this.boxer_name_kor = boxer_name_kor;
	}
	
	
	@Override
	public String toString() {
		return "[ranking=" + ranking + ", boxer_name_kor=" + boxer_name_kor + "]";
	}
	
	
	

	
}
