import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	public static void SendEmail(String recepient)
	{
		
		Properties properties = new Properties();
        System.out.println("preparing Email to send it");
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "YourEmail@gmail.com";
        String mypassword = "YourPassword";
        
        
        Session session = Session.getInstance(properties,
        		new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,mypassword); 
            
            }
        });
        
        Message message = prepareMessage(session,myAccountEmail,recepient);
        
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
        	ex.printStackTrace();
        }
   
        System.out.println("Email send successfuly !!");
        
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		 
        try{

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            message.setSubject("My First Email from Java App");

            message.setText("Hey there , Look my email");
            return message;

        }catch(MessagingException ex){
        	ex.printStackTrace();
        }
		return null;
	}

	
}
