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

import model.reparto.Member;
/**
 * a simple class that allow to send an email using a default gmail account
 * @author Riccardo Soro
 *
 */
public final class MailSender {
	
	private static final String EMAIL="apocalipsenowe@gmail.com";
	private static final String PASSWORD="scout2016";
	
	private MailSender(){
		
	}
	
	/**
	 * send a email to a list of member
	 * @param text the text of the email
	 * @param subject the subject of the email
	 * @param members
	 * @throws MessagingException if the account is wrong
	 */
	
	public static void sendMail(final String text, final String subject, final List<Member> members) throws MessagingException {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		final Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL,PASSWORD);
			}
		});
		for (final Member tmp : members) {
			if (tmp.getTutorMail().isPresent()) {
					final Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(EMAIL));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(tmp.getTutorMail().get()));
					message.setSubject(subject);
					message.setText(text);
					Transport.send(message);
			}
		}
	}
}