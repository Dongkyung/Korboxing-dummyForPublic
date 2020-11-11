package com.box.korBoxing.vo;

/**
 * 페이징 VO
 * 
 * @author dklee
 *
 */
public class PagingVO {
	/** 페이지 번호 */
	private int pageNo;
	/** 페이지당 표시할 컨텐츠 수 */
	private int contentsCountPerPage;
	/** 검색할 문자열 */
	private String searchTarget;
	/** 총 페이지 그룹 수 */
	private int totalPageGroupCount;
	/** 총 페이지 수 */
	private int totalPageCount;
	/** 현재 페이지 그룹 번호 */
	private int currentPageGroupNo;
	/** 페이지 시작 번호 */
	private int startPageNo;
	/** 페이지 종료 번호 */
	private int endPageNo;
	/** 마지막 페이지 번호 */
	private int lastPageNo;
	/** 게시물 시작 번호 */
	private int contentStartNo;
	
	/**체급 */
	private String division;
	/**상태 */
	private String status;
	
	
	public PagingVO() {
		pageNo = 1;
		contentsCountPerPage = 15;
		division="heavyweight";
		status="active";

		
	}
	
	public PagingVO configure(int totalCount) {
		totalPageCount = (int) Math.ceil(((double) totalCount) / contentsCountPerPage);
		totalPageGroupCount = (int) Math.ceil(totalPageCount / 10.0);
		this.lastPageNo = totalPageCount;
		this.currentPageGroupNo = (pageNo - 1) / 10;
		this.startPageNo = currentPageGroupNo == 0 ? 1 : currentPageGroupNo * 10 + 1;
		this.endPageNo = (currentPageGroupNo + 1) * 10 < totalPageCount ? (currentPageGroupNo + 1) * 10 : totalPageCount;
		this.endPageNo = this.endPageNo < 1 ? 1 : this.endPageNo;
		this.contentStartNo = totalCount - getOffset();
		return this;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getContentsCountPerPage() {
		return contentsCountPerPage;
	}

	public void setContentsCountPerPage(int contentsCountPerPage) {
		this.contentsCountPerPage = contentsCountPerPage;
	}

	public int getOffset() {
		return (pageNo - 1) * contentsCountPerPage;
	}

	public String getSearchTarget() {
		return searchTarget;
	}

	public void setSearchTarget(String searchTarget) {
		this.searchTarget = searchTarget;
	}
	
	public boolean hasNextGroup() {
		return currentPageGroupNo < totalPageGroupCount;
	}
	
	public boolean hasPreviousGroup() {
		return 0 < currentPageGroupNo;
	}
	
	public int getNextGroupPage() {
		int nexPageNo = this.pageNo;
		
		if (hasNextGroup()) {
			nexPageNo = this.pageNo + 10 < lastPageNo ? this.pageNo + 10 : lastPageNo;
		}
		
		return nexPageNo;
	}
	
	public int getPreviousGroupPage() {
		int previousPageNo = this.pageNo;
		
		if (hasPreviousGroup()) {
			previousPageNo = this.pageNo - 10 > 1 ? this.pageNo - 10 : 1;
		}
		
		return previousPageNo;
	}
	
	public int getNextPage() {
		return pageNo + 1 < totalPageCount ? pageNo + 1 : totalPageCount;
	}
	
	public int getPreviousPage() {
		return pageNo - 1 > 1 ? pageNo - 1 : 1;
	}

	public int getTotalPageGroupCount() {
		return totalPageGroupCount;
	}

	public int getCurrentPageGroupNo() {
		return currentPageGroupNo;
	}
	
	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public int getContentStartNo() {
		return contentStartNo;
	}
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		if(division.equals("")) {
			this.division = "all";
		}else {
			this.division = division.replace("_", " ");
		}

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
