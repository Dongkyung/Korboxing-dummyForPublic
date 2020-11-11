/**************************
 * @since 2020.07.22
 * @author dklee
 * @description 복싱선수 리스트 script file
 **************************/

		
		/************
		Document Ready
		************/
		$(document).ready(function() {
			//Status Button Active/Inactive
			if(!($("#division_btn_group").text()==='선택')){
				let statusLabels = $("#search_status").find("button");
				if (statusValue == 'active') {
					$(statusLabels[0]).addClass("active");
				} else if (statusValue == 'inactive') {
					$(statusLabels[1]).addClass("active");
				} else {
					$(statusLabels[0]).addClass("active");
				}
			}
			
			//체급선택 안내문구 빨간색표시
			if($("#division_btn_group").text()==='선택'){
				$(".dataTables_empty").css("color","red");
			}
				
			
			//DataTable Init
			$('#boxerListTable').DataTable({
				pageLength : 20,
				lengthChange : false,
				processing: true,
				language:{
				    "decimal":        "",
				    "emptyTable":    $("#division_btn_group").text() ==='선택'? "<span style='font-size:20px; font-weight:bold; color:red;'>체급을 선택하세요</span>" :"데이터가 없습니다.",
				    "info":           "총 _TOTAL_ 명 / _START_ - _END_ ",
				    "infoEmpty":      "총 0 명 / 0 - 0",
				    "infoFiltered":   "(_MAX_명으로 부터 검색)",
				    "infoPostFix":    "",
				    "thousands":      ",",
				    "lengthMenu":     "Show _MENU_ entries",
				    "loadingRecords": "로딩 중...",
				    "processing":     "처리 중...",
				    "search":         "검색:",
				    "zeroRecords":    "검색 결과가 없습니다.",
				    "paginate": {
				        "first":      "처음",
				        "last":       "마지막",
				        "next":       ">",
				        "previous":   "<"
				    }
				}
			});
			
			//etc, Init
			$("#loadingBox").css("display", "none");
			$("#userDetailModal").modal('show');
			
			
			//선수정보를 포함한 URL인 경우 선수정보 팝업 즉시표출
			var nowUrl = new URL(window.document.location.href);
			if(nowUrl.searchParams.get('boxerId') !== null) openBoxerDetailModal(nowUrl.searchParams.get('boxerId'));
			
		});
		
		
		/***************		
		변경된 옵션으로 새 선수리스트 호출
		*****************/
		async function newListPage(dataType, dataValue) {
			var isOk = false;
			if(dataType === "status" && $("#division_btn_group").text() ==='선택'){
				sweetAlert("","체급을 먼저 선택하세요.","info");
				return;
			}else if(dataType === "status" &&
						dataValue === "inactive"&&
						 $("#division_btn_group").text() ==='전체'){
							
							await swal.fire({
									title: '',
									html: `<span><b>전체 체급</b>의 <b>은퇴선수</b> 리스트 조회는 데이터를 불러오는데 <b>최대 30초</b>까지 걸릴 수 있습니다. <br>계속하시겠습니까?</span>`,
									type: 'info',
									showCancelButton: true,
									confirmButtonText: '확인',
						 			cancelButtonText: '취소',									
								}).then((result) => {
									if(result.isConfirmed) {
										isOk = true;
									}
								});
			}else{
				isOk = true;
			}
			
			if(isOk){
				var newUrl = location.pathname + "?";
				switch (dataType) {
					case "division":
						newUrl = newUrl + "division=" + dataValue;
						break;
					case "status":
						newUrl = newUrl + "division=" + getParameterByName("division")
								+ "&status=" + dataValue;
						break;
					break;
				}
				location.href = newUrl;
			}
			
		
	}
		