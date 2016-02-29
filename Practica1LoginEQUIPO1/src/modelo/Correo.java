package modelo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {
	private Usuario destinatario;
	private static final String SMTP_AUTH_USER = "ravilan1001@alumno.ipn.mx";
	private static final String SMTP_AUTH_PWD  = "AINR920519";
	

	public Correo() {
		
	}
	
	public void setCorreo(Usuario user){
		String to = user.getCorreo();
		String from = "ravilan1001@alumno.ipn.mx";
		String host = "smtp.office365.com";
		String port = "587";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.port",port);
		Session sesion = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
			}
		});
		
		try{
			MimeMessage mensaje = new MimeMessage(sesion);
			mensaje.setFrom(new InternetAddress(from));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mensaje.setText("Estimad@:"+user.getNombres()+"tu contraseña es: "+user.getContrasena()+"\n Atentamente JAVA");
			Transport.send(mensaje);
			System.out.println("Mensaje enviado");
		}catch (MessagingException mex){
			mex.printStackTrace();
		}
		
	}

}
