package com.prathamproj.service.impl;

import java.io.File;
import java.lang.System.LoggerFinder;
import java.nio.file.FileSystem;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.stereotype.Service;

import com.prathamproj.dto.EmailDetails;
import com.prathamproj.service.impl.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.SentDateTerm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public void sendEmailAlert(EmailDetails emailDetails) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(senderEmail);
			mailMessage.setTo(emailDetails.getRecipient());
			mailMessage.setText(emailDetails.getMessageBody());
			mailMessage.setSubject(emailDetails.getSubject());
			
			javaMailSender.send(mailMessage);
			System.out.println("Mail sent successfully");
		} catch (MailException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void sendEmailWithAttachement(EmailDetails emailDetails) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(senderEmail);
			mimeMessageHelper.setTo(emailDetails.getRecipient());
			mimeMessageHelper.setText(emailDetails.getMessageBody());
			mimeMessageHelper.setSubject(emailDetails.getSubject());
			
			FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
			mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
			javaMailSender.send(mimeMessage);
			
			log.info(file.getFilename() + "has been sent to user with email " + emailDetails.getRecipient()) ;
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
