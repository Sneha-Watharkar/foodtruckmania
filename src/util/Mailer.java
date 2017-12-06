/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;


public class Mailer {


    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            {
        
        try {
            // 1 - get a mail session
            Properties props = new Properties();
            //props.put("mail.smtp.host", "localhost");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", "rc530297@gmail.com");
            props.put("mail.smtp.password", "adminadmin");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
            //props.put("mail.transport.protocol", "smtp");
            //props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
    		//props.put("mail.smtp.port", "587"); //TLS Port
    		//props.put("mail.smtp.auth", "true"); //enable authentication
    		//props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            
            
            // 2 - create a message
            Message message = new MimeMessage(session);
            message.setSubject(subject);
            if (bodyIsHTML) {
                message.setContent(body, "text/html");
            } else {
                message.setText(body);
            }
            
            // 3 - address the message
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            //Transport.send(message);
            // 4 - send the message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com","rc530297@gmail.com", "adminadmin");
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
