package com.tea.service;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.tea.utils.ActividadMailHelper;
import com.tea.utils.InstitucionMailHelper;
import com.tea.utils.ProfesionalMailHelper;

@Service
public class EmailServiceImpl implements EmailService{

	 @Value("${spring.mail.username}")
	 private String userMail;
	 
	 @Value("${spring.mail.password}")
	 private String passwordMail;
	 
	 @Value("${spring.mail.host}")
	 private String host;
	 
	 @Value("${spring.mail.port}")
	 private int port;
	 
	 @Value("${spring.mail.properties.mail.smtp.auth}")
	 private String auth;
	 
	 @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	 private String starttls;
	 

    public void sendmail(String subject, String mensaje, Date date) throws AddressException, MessagingException, IOException {
    	   
    	Properties mailProps = new Properties();
    	
    	mailProps.put("mail.smtp.auth", auth);
    	mailProps.put("mail.smtp.starttls.enable", starttls);
    	mailProps.put("mail.smtp.host", host);
    	mailProps.put("mail.smtp.port", port);
    	   
    	Session session = Session.getInstance(mailProps, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	         return new PasswordAuthentication(userMail, passwordMail);
    	      }
    	   });
    	   
    	   Message msg = new MimeMessage(session);
    	   
    	   msg.setFrom(new InternetAddress(userMail, false));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMail));
    	   msg.setSubject(subject);
    	   msg.setContent(subject, "text/html");
    	   msg.setSentDate(new Date());

    	   MimeBodyPart messageBodyPart = new MimeBodyPart();
    	   messageBodyPart.setContent(mensaje,"text/plain");

    	   Multipart multipart = new MimeMultipart();
    	   multipart.addBodyPart(messageBodyPart);
    	   MimeBodyPart attachPart = new MimeBodyPart();
    	   
    	   URL url = new URL("https://i.ibb.co/2dtZvXn/logo.png");
    	   
    	   attachPart.attachFile(url.getFile());
    	   
    	   attachPart.setDataHandler(new DataHandler(url));
    	   attachPart.setDisposition(Part.ATTACHMENT);
    	   attachPart.setFileName(url.getFile());
    	   
    	   multipart.addBodyPart(attachPart);
    	   msg.setContent(multipart);
    	   
    	   Transport.send(msg);   
    	}
    

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        //System.out.println("Metodo que no quiero implementar");
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        //System.out.println("Metodo que tampoco quiero implementar");
    }
    
    public void armarMailProfesional(ProfesionalMailHelper profesional) throws AddressException, MessagingException, IOException {
    	
    	String mensaje = "A continuación se detallan los datos del profesional cargados por el solicitante para dar " +
    	                 "de alta en el sistema:\n\n" + 
    					 "Solicitante: " +  profesional.getApellidoSolicitante() + ", " + profesional.getNombreSolicitante() + "\n" +  
    					 "Mail Solicitante: " + profesional.getMailSolicitante() + "\n" +
    					 "Tel Solicitante: " + profesional.getTelefonoSolicitante() + "\n" +  
    					 "Profesional: " + profesional.getApellido() + ", " + profesional.getNombre() + "\n" +
    					 "Matrícula: " + profesional.getMatricula() + "\n" +
    					 "Especialidad: " + profesional.getEspecialidad() + "\n" +
    					 "Dirección: " + profesional.getDireccion() + "\n" +
    					 "Localidad: " + profesional.getLocalidad() + "\n" +
    					 "Piso: " + profesional.getPiso() + "\n" + 
    					 "Teléfono: " + profesional.getTelefono() + "\n" +
    					 "Mail: " + profesional.getMail() + "\n" +
    					 "Observaciones: " + profesional.getObservaciones() + "\n\n\n" +
    					 "Mail generado automáticamente. El equipo de TEAyuda.";
    					 
    	this.sendmail("Solicitud de alta de Profesional", mensaje, new Date());
    }
    
    public void armarMailInstitucion(InstitucionMailHelper institucion) throws AddressException, MessagingException, IOException {
    	
    	String mensaje = "A continuación se detallan los datos de la institución cargados por el solicitante para dar " +
    	                 "de alta en el sistema:\n\n" + 
    					 "Solicitante: " +  institucion.getApellidoSolicitante() + ", " + institucion.getNombreSolicitante() + "\n" +  
    					 "Mail Solicitante: " + institucion.getMailSolicitante() + "\n" +
    					 "Tel Solicitante: " + institucion.getTelefonoSolicitante() + "\n" +  
    					 "Nombre: " + institucion.getNombre() + "\n" +
    					 "Descripción: " + institucion.getDescripcion() + "\n" +
    					 "Especialidad: " + institucion.getEspecialidad() + "\n" +
    					 "Dirección: " + institucion.getDireccion() + "\n" +
    					 "Localidad: " + institucion.getLocalidad() + "\n" +
    					 "Teléfono: " + institucion.getTelefono() + "\n" +
    					 "Mail: " + institucion.getMail() + "\n" +
    					 "Observaciones: " + institucion.getObservaciones() + "\n\n\n" +
    					 "Mail generado automáticamente. El equipo de TEAyuda.";

    	this.sendmail("Solicitud de alta de Institución", mensaje, new Date());
    }
    
    public void armarMailActividad(ActividadMailHelper actividad) throws AddressException, MessagingException, IOException {
    	
    	String mensaje = "A continuación se detallan los datos de la actividad cargados por el solicitante para dar " +
    	                 "de alta en el sistema:\n\n" + 
    					 "Solicitante: " +  actividad.getApellidoSolicitante() + ", " + actividad.getNombreSolicitante() + "\n" +  
    					 "Mail Solicitante: " + actividad.getMailSolicitante() + "\n" +
    					 "Tel Solicitante: " + actividad.getTelefonoSolicitante() + "\n" +  
    					 "Nombre: " + actividad.getNombre() + "\n" +
    					 "Descripción: " + actividad.getDescripcion() + "\n" +
    					 "Especialidad: " + actividad.getEspecialidad() + "\n" +
    					 "Dirección: " + actividad.getDireccion() + "\n" +
    					 "Localidad: " + actividad.getLocalidad() + "\n" +
    					 "Teléfono: " + actividad.getTelefono() + "\n" +
    					 "Mail: " + actividad.getMail() + "\n" +
    					 "Observaciones: " + actividad.getObservaciones() + "\n\n\n" +
    					 "Mail generado automáticamente. El equipo de TEAyuda.";
    					 
    	this.sendmail("Solicitud de alta de Actividad", mensaje, new Date());
    }
}
