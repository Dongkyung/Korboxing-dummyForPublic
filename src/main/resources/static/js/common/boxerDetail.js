/**
 * 
 *//*********************
		선수상세팝업 Open
		*********************/
		function openBoxerDetailModal(boxer_id){
        	
			$.ajax({
	            type: "get",
	            url: "/boxer/boxerdetail",
	            data: "boxerId=" + boxer_id,
	            dataType: "json",
	            success: function(result) {
	            	if(result.statusCode ===1){
		            	makeBoxerDetailModal_init();
		            	makeBoxerDetailModal_profile(result.result);
		            	makeBoxerDetailModal_bout(boxer_id);
		            	$("#boxerDetailModal").appendTo("body").modal("show");
						$(".boxerProfileModal").animate( { scrollTop : 0 }, 300 );
							return false;
	            	}else if(result.statusCode ===0){
	            		sweetAlert("선수조회","해당선수의 데이터가 없습니다.","info");
	            	}else{
						sweetAlert("선수조회","알 수 없는 오류입니다.","error");
	            	}
 
	            },error: function(e){
	            	sweetAlert("선수조회","서버에서 에러가 발생했습니다.","error");
	            	console.log(e);
	            }
	        });
			
	 		
		}
		
		/**********************
		선수상세팝업-선수정보 초기화
		************************/
		function makeBoxerDetailModal_init(){
			
			$('#copyProfileUrl').off("click");
			$("#modal-title-boxername").text("");
			$(".card-profileImg .avatar").text("")
			
    		$("#detail_boxerName").text("");
			$("#updateDateSpan").text("");
        	$("#detail_born").text("");
        	$("#detail_career").text("");
        	$("#detail_boxerId").text("");
        	
        	$("#detail_division").text("");
        	$("#detail_height").text("");
        	$("#detail_stance").text("");
      
        	$(".detail-card-body.status").text("");
        	
       		$("#detail_rank").text("");
      		$("#detail_wld").text("");
        	$("#detail_rounds").text("");
        	$("#detail_kos").text("");
        	
        	$("#boutDetailTbody").text("");
		}
		
		
		/**********************
		선수상세팝업-선수정보 화면만들기
		************************/
		function makeBoxerDetailModal_profile(resultSet){
        	$(".card-profileImg .avatar").append(`<img src="/img/boxerProfileImg/${resultSet.boxer_id}.jpg" onerror="if (this.src != 'error.jpg') this.src = '/img/boxerProfileImg/defaultImage.png';" alt="..." class="avatar-img boxer-profile-img rounded">`);
        	
        	let boxerNameForTitle= resultSet.boxer_name_kor? resultSet.boxer_name_kor:resultSet.boxer_name_eng;
        	let boxerNameForDetail;
        	if(resultSet.sex == "M"){
        		boxerNameForDetail =  `<b>${boxerNameForTitle}</b><img src="/img/boxer/male.png" width="15" height="15">`;
        	}else if(resultSet.sex == "F"){
        		boxerNameForDetail = `<b>${boxerNameForTitle}</b><img src="/img/boxer/female.png"  width="15" height="15">`;
        	}
        	
			boxerNameForDetail = !resultSet.boxer_name_kor? 
				boxerNameForDetail+`<button type="button" class="btn btn-primary btn-sm" style="width: 30px; height: 18px; font-size:11px; padding:0px"`
					+`onclick="inputTranslate('${resultSet.boxer_id}', '${resultSet.boxer_name_eng}', '입력','detail')">번역</button>`
				: boxerNameForDetail;
        	$('#copyProfileUrl').on("click",function() {
			var sex = $("#detail_boxerName").find("img").attr("src").split("/")[3];
			var pathname = "";
			if(sex === "male.png"){
				pathname = "/boxer/proboxer/mboxerinfo";
			}else if(sex === "female.png"){
				pathname = "/boxer/proboxer/fboxerinfo";
			}
			var t2 = document.getElementById("copyUrlTemp");
			  $(t2).show();
			  t2.value=`${location.origin}${pathname}?boxerId=${resultSet.boxer_id}`;
			  t2.select();
		
			  document.execCommand('copy');
			  $(t2).hide();
        		  sweetAlert("프로필 복사",`${boxerNameForTitle} 선수의 프로필이 복사되었습니다. \n 원하는 장소에 붙여넣기 하세요.`,"success");
        		});
        	
        	
    		$("#modal-title-boxername").text(`${boxerNameForTitle}`);
    		
    		$("#detail_boxerName").append(boxerNameForDetail);
			$("#updateDateSpan").append(resultSet.lsttm);
			
        	$("#detail_born").text(resultSet.born);
        	$("#detail_career").text(resultSet.career);
        	$("#detail_boxerId").text(resultSet.boxer_id);
        	
        	$("#detail_division").text(resultSet.division);
        	$("#detail_height").text(resultSet.height);
        	$("#detail_stance").text(resultSet.stance);
        	
        
        	let detail_status;
        	if(resultSet.status === '현역'){
        		detail_status = `<label class="btn btn-sm btn-outline-info active state_label_active label_detailInfo">`
				+`<b style="color:#fff !important;">현역</b>`
				+`</label>`	;
        	}else if(resultSet.status === '은퇴'){
        		detail_status = `<label class="btn btn-sm btn-outline-warning active  state_label_inactiv label_detailInfo">`
					+`<b>은퇴</b>`
					+`</label>`	;
        	}
        	
        	
        	$(".detail-card-body.status").append(detail_status);
        	
        	
        	let detail_wld = `<span style='font-weight:bold; font-size:14px; color:blue'>${resultSet.win} 승</span>` 
        						+`- <span style='font-weight:bold; font-size:14px; color:red'>${resultSet.lose} 패</span>`
        						+`- <span style='font-weight:bold; font-size:14px; color:green'>${resultSet.draw} 무</span>`;
        	
        						
   			$("#detail_rank").text(`${resultSet.korea_rank} 위`);
   			$("#detail_wld").append(detail_wld);
        	$("#detail_rounds").text(resultSet.rounds);
        	$("#detail_kos").text(resultSet.KOs);
        	
		}
		
		
		/**********************
		선수상세팝업-경기정보 화면만들기
		************************/
		function makeBoxerDetailModal_bout(boxerId){
			$.ajax({
	            type: "get",
	            url: "/boxer/boutdetail",
	            data: "boxerId=" + boxerId,
	            dataType: "json",
	            success: (result) => {
	            	let boutResultArray = result.result;
	            	boutResultArray.forEach(boutItem => {
	            		
	            		let boutResult_1, boutResult_2,boutResult_2_class, boutResult_3;
	            		switch(boutItem.boutResult){
	            		case "w": boutResult_1 = "승";break;
	            		case "l": boutResult_1 = "패";break;
	            		case "d": boutResult_1 = "무";break;
	            		default : boutResult_1 = "X"
	            		}
	            		
	            		switch(boutItem.boutResultDetail){
	            		case "KO" : boutResult_2 = "KO"; boutResult_2_class="ko"; boutResult_3="KO"; break;
	            		case "TKO" : boutResult_2 = "KO"; boutResult_2_class="ko"; boutResult_3="TKO"; break;
	            		case "RTD" : boutResult_2 = "KO"; boutResult_2_class="ko"; boutResult_3="라운드 닥터중단/기권"; break;
	            		
	            		case "UD" : boutResult_2 = "판정"; boutResult_2_class="de"; boutResult_3="3:0 판정"; break;
	            		case "SD" : boutResult_2 = "판정"; boutResult_2_class="de";  boutResult_3="2:1 판정"; break;
	            		case "MD" : boutResult_2 = "판정"; boutResult_2_class="de";  boutResult_3="2:0 판정 - 1표 동점"; break;
	            		case "TD" : boutResult_2 = "판정"; boutResult_2_class="de";  boutResult_3="4R 이후 경기중단"; break;
	            		case "PTS" : boutResult_2 = "판정"; boutResult_2_class="de";  boutResult_3="포인트 판정"; break;
	            		
	            		case "DQ" : boutResult_2 = "기타"; boutResult_2_class="etc";  boutResult_3="실격"; break;
	            		case "NC" : boutResult_2 = "기타"; boutResult_2_class="etc";  boutResult_3="경기무효"; break;
	            		case "ND" : boutResult_2 = "기타"; boutResult_2_class="etc";  boutResult_3="경기무효"; break;
	            		}

	            	            		
           			 	let boutItem_wld = `<span style='font-weight:bold; font-size:14px; color:blue'>${boutItem.opponentWin} 승</span>` 
 						+`- <span style='font-weight:bold; font-size:14px; color:red'>${boutItem.opponentLose} 패</span>`
 						+`- <span style='font-weight:bold; font-size:14px; color:green'>${boutItem.opponentDraw} 무</span>`;
 						
	            		let boutItemTr = `<tr>`;
            			boutItemTr +=	`<td>${boutItem.boutDate}</td>`;
            			 boutItemTr += `<td><a href="javascript:openBoxerDetailModal('${boutItem.opponentId}');" style="color:#575962; font-weight:bold;" >`;
            			 boutItemTr += boutItem.opponentNameKor? boutItem.opponentNameKor: boutItem.opponentName;
            			 boutItemTr += `</a></td>`;
            			 boutItemTr +=	`<td>${boutItem_wld}</td>`
            			 boutItemTr +=	`<td style="line-height:30px">`
						+`<div style="display: inline-block; vertical-align: middle;">`
						+`<label class="boutResultLabel_1 ${boutItem.boutResult}">${boutResult_1}</label>`
            			 + `<div class="boutResultDetail">`
            			 + `<span class="boutResultLabel_2 ${boutItem.boutResult} ${boutResult_2_class}">${boutResult_2}</span><br>`
            			 + `<span class="boutResultLabel_3 ${boutItem.boutResult} ${boutResult_2_class}">(${boutResult_3})</span></td>`
						+`</div>`
						+`</div>`
            			 boutItemTr +=	`<td>${boutItem.rounds}</td>`
//            			 boutItemTr +=	`<td>${boutItem.lsttmF}</td>`
            			 boutItemTr +=`</tr>`
	            		
	            		$("#boutDetailTbody").append(boutItemTr);
	            	});
	            },
	            error: ((e) => {
	            	sweetAlert("경기정보 조회", "서버에서 에러가 발생하였습니다.","error");
	            	console.log(e);
	            }),
	        });			
		}