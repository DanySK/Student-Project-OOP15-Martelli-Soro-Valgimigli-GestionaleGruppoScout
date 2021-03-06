package extra.mail;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

import model.escursioni.Excursion;
import model.reparto.Member;

/**
 * Class that provides methods which furnish text of mail
 * 
 * @author Valgio
 *
 */

public final class ControlMail {

	private ControlMail() {

	}

	/**
	 * Static method that provides a text for the mail for the creation of
	 * excursion
	 * 
	 * @param exc
	 * @throws MessagingException
	 */
	public static void sendMailForExcursion(final Excursion exc) throws MessagingException {
		String text = "AVVISO USCITA" + System.lineSeparator();
		text += "Salve, con la seguente mail si vuole avvisare dell'evento: " + exc.getName() + System.lineSeparator();
		text += "Previsto il " + exc.getDateStart() + " e terminer�: " + exc.getDateEnd() + System.lineSeparator();
		text += "Locazione : " + exc.getPlace() + "Prezzo: " + exc.getPrize() + System.lineSeparator();
		text += "Ci si divertir� un secchio grande quindi non far mancare tuo figlio";
		MailSender.sendMail(text, "USCITA", exc.getAllPartecipants());
	}

	/**
	 * Static method that provides a text for the mail for birthday
	 * 
	 * @param member
	 * @throws MessagingException
	 */
	public static void sendMailForBirthday(final List<Member> member) throws MessagingException {
		final String text = "Auguri di compleanno dalla tua staff!!! Ci vediamo ad attivit� ;)";
		MailSender.sendMail(text, "AUGURI DI COMPLEANNO", member);
	}

	/**
	 * Static method that provides a text for the mail for getting payment of
	 * excursion
	 * 
	 * @param exc
	 * @throws MessagingException
	 */
	public static void sendMailForPaymentExcursion(final Excursion exc) throws MessagingException {
		String text = "Salve, sollecitiamo il pagameno dell'uscita " + exc.getName() + System.lineSeparator();
		text += "in data: " + exc.getDateStart() + System.lineSeparator();
		text += "Prezzo: " + exc.getPrize();
		text += "Non ci risulta il pagamento." + System.lineSeparator() + " Saluti :)";
		MailSender.sendMail(text, "PAGAMENTO USCITA", exc.getNotPaied());
	}

	/**
	 * Static method that provides a text for the mail for getting payment of
	 * annual tax
	 * 
	 * @param members
	 * @param end
	 * @throws MessagingException
	 */
	public static void sendMailForTaxPayment(final List<Member> members, final LocalDate end)
			throws MessagingException {
		String text = "AVVISO PAGAMENTO TASSA ANNUALE" + System.lineSeparator();
		text += "Salve, non ci risulta il pagamento della tassa annuale di: 50 Euro " + System.lineSeparator();
		text += "Ricordiamo che la data di scadenza �: " + end.toString() + System.lineSeparator();
		text += "Saluti :)";
		MailSender.sendMail(text, "PAGAMENTO TASSA ANNUALE", members);
	}

}
