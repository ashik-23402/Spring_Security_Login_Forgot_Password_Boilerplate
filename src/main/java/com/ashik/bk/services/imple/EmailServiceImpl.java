package com.ashik.bk.services.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ashik.bk.services.EmailDetails;
import com.ashik.bk.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMail;

	@Override
	public void SendEmail(EmailDetails emaildetails)  {
		// TODO Auto-generated method stub
		
		MimeMessage message = javaMail.createMimeMessage();
		
		try {
			message.setFrom(new InternetAddress(emaildetails.getFrom()));
			message.setRecipients(MimeMessage.RecipientType.TO, emaildetails.getTo());
			message.setSubject(emaildetails.getSubject());
			
			message.setContent(emaildetails.getMssgContent(),"text/html; charset=utf-8");
			
			javaMail.send(message);
			
			
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
