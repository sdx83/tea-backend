package com.tea.utils;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailSenderProperties {

	 @Value("${spring.mail.username}")
	 private String userMail;
	 
	 @Value("${spring.mail.password}")
	 private String passwordMail;
	 
	 @Value("${spring.mail.host}")
	 private String host;
	 
	 @Value("${spring.mail.port}")
	 private int port;
	 
    @Bean
    public JavaMailSender getJavaMailSender(){
    	
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(userMail);
        mailSender.setPassword(passwordMail);

        Properties properties = mailSender.getJavaMailProperties();
        
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");

        return mailSender;
    }
}
