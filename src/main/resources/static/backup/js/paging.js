'use strict';

function Paging() {}

Paging.prototype.previousPage = function(existFlag, pageNo) {
	if (existFlag) {
		this.movePage(pageNo - 1);
	}
};

Paging.prototype.nextPage = function(existFlag, pageNo) {
	if (existFlag) {
		this.movePage(pageNo + 1);
	}
};

Paging.prototype.movePage = function(pageNo) {
	var form = document.searchForm;
	location.href = location.pathname
	  + '?'
	  + 'searchType=' + form.searchType.value
	  + this.decideSearchType()
	  + this.decideContentCountPerPage()
	  + this.getSortDirection()
	  + '&pageNo=' + pageNo;
}




//단일검색, 범위검색 판별
Paging.prototype.decideSearchType = function(){
	var form = document.searchForm;
	var urlDetailPath;
	if(typeof  form.searchRangeValues == 'undefined'){
		urlDetailPath = '&searchValue=' + form.searchValue.value
	}else if(typeof  form.searchValue == 'undefined'){ 
		urlDetailPath = '&searchRangeValues=' + form.searchRangeValues[0].value
							+ '&searchRangeValues=' + form.searchRangeValues[1].value;
	}
	return urlDetailPath;
}

//페이지당 컨텐츠갯수 판별
Paging.prototype.decideContentCountPerPage = function(){
	var form = document.searchForm;
	var contentCount;
	if(typeof form.contentsCountPerPage == 'undefined'){
		contentCount = '&contentsCountPerPage=15'
	}else{
		contentCount = '&contentsCountPerPage=' + form.contentsCountPerPage.value
	}
	return contentCount
}

// 정렬방향 가져오기
Paging.prototype.getSortDirection = function(){
	var urlString = document.location.href;
	var url = new URL(urlString);
	var paramDirection = url.searchParams.get('sortDirection');
	var paramValue = url.searchParams.get('sortValue');
	if(paramDirection==null){
		paramDirection = 'desc';
	}
	if(paramValue==null){
		paramValue = 'sort_seq';
	}
	
	var sortParam = '&sortValue=' + paramValue + '&sortDirection=' + paramDirection;
	return sortParam;
}



/**정렬 오름/내림차순 토글**/
Paging.prototype.toggleDirection = function(sortValue) {
	var form = document.searchForm;
	var $trDirection = $('#'+sortValue);
	var sortDirection = '';
	if($trDirection.hasClass('desc')){
		sortDirection = 'asc';
	}else if($trDirection.hasClass('asc')){
		sortDirection ='desc';
	}
	
	location.href = location.pathname
	+ '?'
	+ 'searchType=' + form.searchType.value
	+ this.decideSearchType()
	+ this.decideContentCountPerPage()
	+ '&pageNo=0'
	+ '&sortValue=' + sortValue
	+ '&sortDirection='+sortDirection;
}


/** class에 정렬 토글**/
Paging.prototype.sortSet = function(){
	var urlString = document.location.href;
	var url = new URL(urlString);
	var paramDirection = url.searchParams.get('sortDirection');
	var paramValue = url.searchParams.get('sortValue');
	var sortTr = $('.sortValue');
	
	//get파라미터 이름으로 TR에 Sort방향 클래스 부여
	if(paramDirection==null){
		sortTr.addClass('desc');
	}else if(paramDirection === 'asc'){
		sortTr.addClass('asc');
	}else if(paramDirection === 'desc'){
		sortTr.addClass("desc");			
	}
	
	//클릭된 TR on클래스 부여
	sortTr.removeClass('sortOn');
	if(paramValue == null){
		$("#sort_seq").addClass('sortOn');
	}else{
		$('#'+paramValue).addClass('sortOn');
	}
	
	//on된 TR을 제외하고는 마우스오버 이벤트
	$(".sortValue:not(.sortOn)").hover(function(e){
		$('#'+e.target.id).css('color', '#FF9800');
	},function(e){
		$('#'+e.target.id).css('color', '#ffffff');
	});
	
	
}



var paging = new Paging();