package com.tea.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String MAIL_CONSTANT="noreply@teayuda.com";


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
    
    public void armarMailInstitucion(InstitucionMailHelper institucion) {
    	
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
    					 
    	this.sendOnly("appteayuda@gmail.com", "Solicitud de alta de Institución", mensaje, new Date());
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
