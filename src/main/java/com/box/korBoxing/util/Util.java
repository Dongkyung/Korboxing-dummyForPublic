package com.box.korBoxing.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class Util {

	private static final String MESSAGE_FORMAT = "{\"isSuccess\": %s, \"message\": \"%s\"}";
	
	
	/**
	 * 에러 메세지 생성
	 * 
	 * @param errors
	 * @return
	 */
	public String createErrorMessage(List<FieldError> errors) {
		StringBuilder errorMessage = new StringBuilder();
		
		if (errors == null) {
			errorMessage.append("문의에 실패하였습니다.");
		} else {
			for (FieldError error : errors) {
				errorMessage.append(error.getDefaultMessage()).append(",");
			}
		}
		
		return new StringBuilder(String.format(MESSAGE_FORMAT, false, errorMessage.toString())).toString();
	}
	
	
	/**
	 * 성공 메세지 생성
	 * 
	 * @return
	 */
	public String createSuccessMessage() {
		return new StringBuilder(String.format(MESSAGE_FORMAT, true, "문의를 성공적으로 발송하였습니다.")).toString();
	}
	
	/**
	 * SMTP 로 메일 보내기
	 * 
	 */
	public boolean sendMail(String destinationEmail,String title, String contentBody ) {
		
		boolean result = false;
		final String mailUser = "korboxing@naver.com"; //발신자의 이메일 아이디를 입력
		final String mailPassword = "1q2w3e4rdk"; //발신자 이메일의 패스워드를 입력
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPassword);
            }
        });
		
		try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailUser));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail)); 

            // Subject
            message.setSubject(title); //메일 제목을 입력

            // Text
            message.setText(contentBody);    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
            return true;
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}
}
