package com.tea.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tea.utils.ActividadMailHelper;
import com.tea.utils.InstitucionMailHelper;
import com.tea.utils.ProfesionalMailHelper;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String MAIL_CONSTANT="appteayuda@gmail.com";


    public void sendOnly(String to, String subject, String text, Date date) throws MailException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(MAIL_CONSTANT);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        //Aca le podemos mandar que fecha quiere mandarlo.
        simpleMailMessage.setSentDate(date);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendMail(String to,String subject,Date date,String text) throws MailException{
    	
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(MAIL_CONSTANT);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSentDate(date);

        javaMailSender.send(simpleMailMessage);

    }
    
    public void sendmail(String mensaje) throws AddressException, MessagingException, IOException {
    	   Properties props = new Properties();
    	   props.put("mail.smtp.auth", "true");
    	   props.put("mail.smtp.starttls.enable", "true");
    	   props.put("mail.smtp.host", "smtp.gmail.com");
    	   props.put("mail.smtp.port", "587");
    	   
    	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	         return new PasswordAuthentication(MAIL_CONSTANT, "AppTEA2021$");
    	      }
    	   });
    	   
    	   Message msg = new MimeMessage(session);
    	   msg.setFrom(new InternetAddress(MAIL_CONSTANT, false));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MAIL_CONSTANT));
    	   msg.setSubject("Tutorials point email");
    	   msg.setContent("Tutorials point email", "text/html");
    	   msg.setSentDate(new Date());

    	   MimeBodyPart messageBodyPart = new MimeBodyPart();
    	   messageBodyPart.setContent(mensaje,"text/plain");
    	   //messageBodyPart.setContent(mensaje, "text/html");

    	   Multipart multipart = new MimeMultipart();
    	   multipart.addBodyPart(messageBodyPart);
    	   MimeBodyPart attachPart = new MimeBodyPart();

    	   attachPart.attachFile("/home/sgonzalez/Escritorio/TEAyudaLetrasAzules.png");
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
    
    public void armarMailProfesional(ProfesionalMailHelper profesional) {
    	
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
    					 
    	this.sendOnly("appteayuda@gmail.com", "Solicitud de alta de Profesional", mensaje, new Date());
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

    	this.sendmail(mensaje);
    	//this.sendOnly("appteayuda@gmail.com", "Solicitud de alta de Institución", mensaje, new Date());
    }
    
    public void armarMailActividad(ActividadMailHelper actividad) {
    	
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
    					 
    	this.sendOnly("appteayuda@gmail.com", "Solicitud de alta de Actividad", mensaje, new Date());
    }
}
