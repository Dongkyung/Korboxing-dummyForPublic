

$(document).ready(function() {
	/**
		Path로 파악하여 메뉴 open 및 active처리 
	 */
	let naviItemList = $("div.sidebar-content").find("li.nav-item");
	let pathName = window.location.pathname;
	if(pathName.indexOf("/boxing/probox") !== -1){
		$(naviItemList[0]).addClass('active');
		if(pathName.indexOf("faq") !== -1)  					$($(naviItemList[0]).find("li")[0]).addClass("active");
		else if(pathName.indexOf("etc") !== -1)  			$($(naviItemList[0]).find("li")[1]).addClass("active");
	
	}else if(pathName.indexOf("/boxing/armabox") !== -1){ 
		$(naviItemList[1]).addClass('active');
		if(pathName.indexOf("faq") !== -1)  					$($(naviItemList[1]).find("li")[0]).addClass("active");
		else if(pathName.indexOf("etc") !== -1) 			$($(naviItemList[1]).find("li")[1]).addClass("active");
		
	}else if(pathName.indexOf("/organization/organkor") !== -1){ 
		$(naviItemList[2]).addClass('active');
		if(pathName.indexOf("info") !== -1)  					$($(naviItemList[2]).find("li")[0]).addClass("active");
		else if(pathName.indexOf("champ") !== -1) 		$($(naviItemList[2]).find("li")[1]).addClass("active");
	
	}else if(pathName.indexOf("/boxer/proboxer") !== -1) {
		$(naviItemList[3]).addClass('active');
		if(pathName.indexOf("hof") !== -1)  					$($(naviItemList[3]).find("li")[0]).addClass("active");
		else if(pathName.indexOf("mboxerinfo") !== -1)	$($(naviItemList[3]).find("li")[1]).addClass("active");
		else if(pathName.indexOf("fboxerinfo") !== -1)	$($(naviItemList[3]).find("li")[2]).addClass("active");
	
	}else if(pathName.indexOf("/boxer/amaboxer") !== -1) {
		$(naviItemList[4]).addClass('active');
		if(pathName.indexOf("hof") !== -1)  					$($(naviItemList[4]).find("li")[0]).addClass("active");
		else if(pathName.indexOf("mboxerinfo") !== -1)	$($(naviItemList[4]).find("li")[1]).addClass("active");
		else if(pathName.indexOf("fboxerinfo") !== -1)	$($(naviItemList[4]).find("li")[2]).addClass("active");
	
	}else if(pathName.indexOf("/item/glove") !== -1) {
		$(naviItemList[5]).addClass('active');
	
	}else if(pathName.indexOf("/item/gear") !== -1) {
		$(naviItemList[6]).addClass('active');
	
	}else if(pathName.indexOf("/item/etc") !== -1) {
		$(naviItemList[7]).addClass('active');
	
	}else if(pathName.indexOf("/gym/gyminfo") !== -1) { 
		$(naviItemList[8]).addClass('active');
	}
	
	naviItemList.find('li.active').parent().parent().addClass("show");	
	
	
});




function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, '\\$&');
	var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'), results = regex.exec(url);
	if (!results)
		return '';
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function sweetAlert(title,text,icon){
	swal.fire({
		title: title,
		text: text,
		icon: icon, //warning, error, success, info
		showCloseButton: true,
		confirmButtonText:`확인`,
		customClass: {
    		confirmButton: `btn btn-${icon}`,
 		 },
		
	});
}


function openInquiryModal(){
	$("#inquiryFade").appendTo("body").modal("show");
}


function sendInquiry() {
		var form = document.contactForm;
		
		if (!form.nickName.value.trim()) {
			sweetAlert("","닉네임은 필수 입력사항입니다.","info");
		} else if (!form.email.value.trim()) {
			sweetAlert("","이메일은 필수 입력사항입니다.","info");
		} else if (!form.inquiryContent.value || !form.inquiryContent.value.trim()) {
			sweetAlert("","내용은 필수 입력사항입니다.","info");
		} else {
			var destinationEmail = '';
			destinationEmail = "ldk2468@naver.com"
			
			$.ajax({
				type: 'POST',
				url: '/inquiry',
				data: {
					nickName: form.nickName.value.trim(),
					userName: form.userName.value.trim(),
					phoneNumber: form.phoneNumber.value.trim(),
					email: form.email.value.trim(),
					destinationEmail: destinationEmail,
					category: form.getElementsByClassName("active")[0].innerText.trim(),
					content: form.inquiryContent.value.trim()
				},
				dataType: 'json',
				success: function(result) {
					if (result.isSuccess) {
						$('.inquiryModal').find('button.modal-close').trigger('click');
						sweetAlert("",result.message,"success");
					} else {
						sweetAlert("",result.message,"danger");
					}
				},
				error: function(code, status, xhr) {
					console.log(code, status, xhr);
					sweetAlert("","네트워크 통신중 오류가 발생하였습니다. 잠시후 다시 시도해주세요.","danger");
				}
			});
		}
	};
	
	
	
	/****************
		빠른 선수찾기 기능
		****************/
function quickFindBoxer(){
	var findForm = document.boxerSearchForm;
	var boxerId = "";
	if(!findForm.boxerName.value.trim()){
		sweetAlert("","검색할 선수의 이름을 입력해주세요.","info");
	}else{
		$.ajax({
				type: 'GET',
				url: `/bot/boxerinfo?boxerName_kor=${findForm.boxerName.value.trim()}`,
				dataType: 'text',
				success: function(result) {
					console.log(result);
					var resultArr = result.split("first")[1].split("end")[0].split("기준날짜");
					if(resultArr.length <= 1){
						sweetAlert("","선수 조회 결과가 없습니다.","info");
						return false;
					}else if(resultArr.length > 2){
//						sweetAlert("","선수가 한명 이상입니다.","info");

						var selectBoxerTable =
						`<table class="table  table-hover">
							<tr>
								<th>체급</th>
								<th>이름</th>
								<th>전적</th>
							</tr>`;
							
							resultArr.forEach(item=>{
							    if(item === "--") return false;
							    var division = item.split("--")[3].trim().split(": ")[1];
								var boxerNameKor = item.split("--")[2].trim().split(": ")[1];
								var wld = item.split("--")[5].trim().split(": ")[1];
								var boxerId = item.split("--")[1].trim().split(": ")[1];
								
								selectBoxerTable = selectBoxerTable +
									`<tr onclick="swal.clickCancel(); openBoxerDetailModal(${boxerId});">
										<td>${division}</td>
										<td>${boxerNameKor}</td>
										<td>${wld}</td>
									</tr>`
							});
							selectBoxerTable = selectBoxerTable + '</table>'
							
						swal.fire({
							  title: '찾으시는 선수를 선택하세요.',
							  html: selectBoxerTable,
								showCancelButton: false,
			  					showConfirmButton: false,
						});
						return false;
					}else {
						boxerId = resultArr[1].split("--")[1].trim().split(": ")[1];
					}
					openBoxerDetailModal(boxerId);
				},
				error: function(code, status, xhr) {
					console.log(code, status, xhr);
					sweetAlert("","네트워크 통신중 오류가 발생하였습니다. 잠시후 다시 시도해주세요.","danger");
				}
			});
	}
}


		/****************
		번역지원 기능
		****************/
		function inputTranslate(boxerId, boxerNameEng, mode, from){
			event.stopPropagation();
			var boxerNameKor = "";
			
			$("#boxerDetailModal").modal("hide");
					swal.fire({
						title: `${boxerNameEng} 선수의 한글 이름을 입력해주세요.`,
						input: 'text',
						inputAttributes: {
						  autocapitalize: 'off'
						},
						 showCancelButton: true,
  						 confirmButtonText: '확인',
						 cancelButtonText: '취소'
						
					}).then( result => {
						var value = result.value;
						if(result.isConfirmed) {
							boxerNameKor = value;
							if(boxerNameKor === ""){
								sweetAlert("", "빈 값을 입력할 수 없습니다.", "warning");
								if(from === 'detail'){
									$("#boxerDetailModal").appendTo("body").modal("show");
								}
							}else{
								swal.fire({
									title: `${boxerNameEng} -> ${value}`,
									text: `등록하시겠습니까?`,
									type: 'success',
									showCancelButton: true,
									confirmButtonText: '확인',
						 			cancelButtonText: '취소',									
								}).then((result) => {
									if(result.isConfirmed) {
										$.ajax({
										    url:'/supportTranslate',
										    async:true,
										    type:'POST', 
										    data: {
										        boxerId: boxerId,
										        boxerNameKor:boxerNameKor,
										        boxerNameEng:boxerNameEng,
										        mode:mode
										    },
										    dataType:'text',// xml, json, script, html
										    beforeSend:function(jqXHR) {},// 서버 요청 전 호출 되는 함수 return false; 일 경우 요청 중단
										    success:function(jqXHR) {
										    	sweetAlert("번역등록 완료","등록해주셔서 감사합니다.\n검토 후 반영하겠습니다.","success");
												if(from === 'detail'){
													$("#boxerDetailModal").appendTo("body").modal("show");	
												}
										    },// 요청 완료 시
										    error:function(jqXHR) {},// 요청 실패.
										    complete:function(jqXHR) {}// 요청의 실패, 성공과 상관 없이 완료 될 경우 호출
										});
									}else if(from === 'detail'){
										$("#boxerDetailModal").appendTo("body").modal("show");
									}
								});
							}	
						}else if(from === 'detail'){
							$("#boxerDetailModal").appendTo("body").modal("show");
						}
					}
				);

		}

