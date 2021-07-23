/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;


import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class RPass {
    
     public static void sendEmail(String recepient, String newPass) throws MessagingException {

         Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

      Session session = Session.getDefaultInstance(props,
                  new javax.mail.Authenticator() {
                       protected PasswordAuthentication getPasswordAuthentication() 
                       {
                             return new PasswordAuthentication("prisound.help@gmail.com", "rumoao20");
                       }
                  });
      /** Ativa Debug para sessão */
      session.setDebug(true);
      try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prisound.help@gmail.com")); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                       .parse(recepient);  
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setText("A sua nova password é " +newPass);
            
            /**Método para enviar a mensagem criada*/
            Transport.send(message);
       } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
  }
    
}
