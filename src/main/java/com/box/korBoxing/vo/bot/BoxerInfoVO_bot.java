package com.box.korBoxing.vo.bot;

public class BoxerInfoVO_bot {
	private String date;
	private String ranking;
	private String korea_rank;
	private String division_kor;
	private String boxer_name_kor;
	private String win;
	private String lose;
	private String draw;
	private String status;
	private String boxer_id;
	
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
	public String getKorea_rank() {
		return korea_rank;
	}
	public void setKorea_rank(String korea_rank) {
		this.korea_rank = korea_rank;
	}
	public String getDivision_kor() {
		return division_kor;
	}
	public void setDivision_kor(String division_kor) {
		this.division_kor = division_kor;
	}
	public String getBoxer_name_kor() {
		return boxer_name_kor;
	}
	public void setBoxer_name_kor(String boxer_name_kor) {
		this.boxer_name_kor = boxer_name_kor;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getLose() {
		return lose;
	}
	public void setLose(String lose) {
		this.lose = lose;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBoxer_id() {
		return boxer_id;
	}
	public void setBoxer_id(String boxer_id) {
		this.boxer_id = boxer_id;
	}
	
	@Override
	public String toString() {
		return "BoxerInfoVO_bot [date=" + date + ", ranking=" + ranking + ", korea_rank=" + korea_rank
				+ ", division_kor=" + division_kor + ", boxer_name_kor=" + boxer_name_kor + ", win=" + win + ", lose="
				+ lose + ", draw=" + draw + ", status=" + status + ", boxer_id=" + boxer_id + "]";
	}
	
	
	
	
	
		
}
