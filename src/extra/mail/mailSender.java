package extra.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Member;

public class MailSender {

	public static void sendMail(final String text, final String subject, final List<Member> members) {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("apocalipsenowe@gmail.com", "scout2016");
			}
		});
		for (Member tmp : members) {
			if (tmp.getTutorMail().isPresent()) {
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("apocalipsenowe@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(tmp.getTutorMail().get()));
					message.setSubject(subject);
					message.setText(text);
					Transport.send(message);
				} catch (MessagingException e) {
				}
			}
		}
	}
}