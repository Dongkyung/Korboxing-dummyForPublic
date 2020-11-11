/*
* date : 2019 . 05. 06
* author : dklee
* description : 
* 페이지에서 사용하는 함수 정의
* 
*/ 

'use strict';

var korBoxing = {};

(function() {
	

	korBoxing.contactUs = function() {
		var contactUs = document.getElementById('contact-us');
		$('#inquiry-category').find('a').removeClass('on');
		contactUs.classList.toggle('disNone');
	};
	
	
	korBoxing.sendInquiry = function() {
		var form = document.contactForm;
		
		if (!form.nickName.value.trim()) {
			alert('닉네임은 필수 입력사항입니다.');
		} else if (!form.email.value.trim()) {
			alert('이메일은 필수 입력사항입니다.');
		} else if (!form.inquiryContent.value || !form.inquiryContent.value.trim()) {
			alert('내용은 필수 입력사항입니다.')
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
					category: form.getElementsByClassName("on")[0].innerText.trim(),
					content: form.inquiryContent.value.trim()
				},
				dataType: 'json',
				success: function(result) {
					if (result.isSuccess) {
						$('#contact-us').find('a.btn_close').trigger('click');
						alert(result.message);
					} else {
						alert(result.message.replace(/\,/g, '\n'));
					}
				},
				error: function(code, status, xhr) {
					console.log(code, status, xhr);
					alert('네트워크 통신중 오류가 발생하였습니다. 잠시후 다시 시도해주세요.');
				}
			});
		}
	};
	

}());




function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, '\\$&');
	var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'), results = regex.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
