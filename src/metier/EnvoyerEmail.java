
package metier;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerEmail {
  public  int envoyezMessage(String monEmail,String username,String password,String mail,String sujet,String lemessage) {
		int a=0;
		try {
                Properties props = new Properties();
                 props.setProperty("mail.smtp.host", "smtp.gmail.com");
     props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     props.setProperty("mail.smtp.socketFactory.fallback", "false");
     props.setProperty("mail.smtp.port", "465");
     props.setProperty("mail.smtp.socketFactory.port", "465");
     props.put("mail.smtp.auth", "true");
     props.put("mail.debug", "true");
     props.put("mail.store.protocol", "pop3");
     props.put("mail.transport.protocol", "smtp");
		

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(monEmail,password);
				}
			});

                        a=1;
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(monEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject(sujet);
			message.setText(lemessage);

			Transport.send(message);

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}
                return a;
	}
}  

