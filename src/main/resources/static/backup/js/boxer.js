/**
 * 선수정보  js
 */

var division_g;
var status_g;

/**
 * 페이지 로드되고 난 뒤 동작할 기능
 */
function init(){
	// 페이지 파라미터 변수에 데이터 할당
	if(getParameterByName("division") == "undefined" || getParameterByName("division") == null){
		division_g = "";
	}else{
		division_g = getParameterByName("division");
	}
	if(getParameterByName("status") == "undefined" || getParameterByName("status") == null){
		status_g = "";
	}else{
		status_g = getParameterByName("status");
	}
	
//	//체급 드롭박스에 문자열 세팅
//	if(division_g == null){
//		$("#division_btn_group").text("체급")
//	}else{
//		$("#division_btn_group").text(division_g)
//	}
	
	//상태 라디오버튼 on/off
	var radio_btn_classes = "btn-default btn-primary btn-success btn-info btn-warning btn-danger btn-link";
	if(status_g == "stat_active"){ //현역
		$("#stat_active").removeClass('notActive '+radio_btn_classes).addClass('active btn-primary');
		$("#stat_inactive").removeClass('active '+radio_btn_classes).addClass('notActive btn-default');
	}else if(status_g == "stat_inactive"){ //은퇴
		$("#stat_active").removeClass('active '+radio_btn_classes).addClass('notActive btn-default');
		$("#stat_inactive").removeClass('notActive '+radio_btn_classes).addClass('active btn-info');
	}
	
	//상태 라디오버튼 이벤트
    $('#radioBtnV2 span').on('click', function(){
    	newListPage("status",$(this).attr("id"))
    });
}

/**
 * 체급 , 상태 변경 시 선수리스트 재호출
 * 
 */
function newListPage(dataType, dataValue){
	var newUrl = location.pathname +"?";
	switch(dataType){
	case "division":
		newUrl = newUrl + "division="+dataValue+"&status="+status_g;  break;
	case "status":
		newUrl = newUrl + "division="+division_g+"&status="+dataValue;  break;
		break;
	}
	location.href=newUrl;
}



//$(document).ready(function(){
//    $('#radioBtnV2 span').on('click', statusChange);
//});
//
////현역-은퇴 radio 이벤트
//function statusChange(){
//	console.log($(this));
//	console.log($(this).id);
//	var a = $(this);
//    var sel = $(this).data('value');
//    var tog = $(this).data('toggle');
//    var active = $(this).data('active');
//    var classes = "btn-default btn-primary btn-success btn-info btn-warning btn-danger btn-link";
//    var notactive = $(this).data('notactive');
////    $('#'+tog).val(sel);
//    $('span[data-toggle="'+tog+'"]').not('[data-value="'+sel+'"]').removeClass('active '+classes).addClass('notActive '+notactive);
//    $('span[data-toggle="'+tog+'"][data-value="'+sel+'"]').removeClass('notActive '+classes).addClass('active '+active);
//};