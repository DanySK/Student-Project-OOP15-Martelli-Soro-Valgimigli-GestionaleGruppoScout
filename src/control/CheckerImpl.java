package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import extra.mail.ControlMail;
import model.escursioni.Excursion;
import model.reparto.Member;
import model.reparto.Reparto;

/**
 * 
 * @author Valgio
 *
 */
public class CheckerImpl implements Checker, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321120264672768555L;
	private static final Integer DAYTOCHECK = 7;
	private static final Integer TODAY = 1;

	@Override
	public List<Member> noPaied(final Excursion exc) {
		return exc.getNotPaied();
	}

	@Override
	public List<Member> birthday(final int nDay, final List<Member> people) {
		final LocalDate now = LocalDate.now();
		final LocalDate limit = LocalDate.now().plus(nDay, ChronoUnit.DAYS);

		return people.stream().filter(e -> this.checkDateIsBetween(now, limit, e.getBirthday()))
				.collect(Collectors.toList());

	}

	@Override
	public List<Excursion> excursionInProgram(final int nDay, final List<Excursion> excursions) {
		final LocalDate now = LocalDate.now();
		final List<Excursion> exc = new ArrayList<>();
		for (int i = 0; i < nDay; i++) {
			for (final Excursion e : excursions) {
				if (e.getDateStart().equals(now.plus(i, ChronoUnit.DAYS))) {
					exc.add(e);
				}
			}
		}
		return exc;
	}

	@Override
	public List<Member> noPaiedMembers(final Reparto rep) {
		return rep.getMembersNotPaid(Year.now().getValue());
	}

	@Override
	public Map<String, List<String>> stdRouting(final Unit unit) {
		final List<Member> people = unit.getContainers().getMembers();
		final List<Excursion> excursions = unit.getContainers().getExcursion();
		final List<Excursion> toRemove = excursions.stream().filter(e -> e.getDateEnd().compareTo(LocalDate.now()) < 0)
				.collect(Collectors.toList());
		toRemove.forEach(e -> unit.removeExcursion(e));

		final Map<String, List<String>> map = new HashMap<>();
		final List<Excursion> exc = this.excursionInProgram(DAYTOCHECK, excursions);
		final boolean mail = this.checkMail(unit);
		for (final Excursion e : exc) {
			map.put("Evento del " + e.getDateStart() + ": " + e.getName(),
					e.getNotPaied().stream().map(m -> m.getName() + " " + m.getSurname()).collect(Collectors.toList()));
			if (this.checkDateIsBetween(LocalDate.now(), LocalDate.now().plus(DAYTOCHECK, ChronoUnit.DAYS),
					e.getDateStart()) && mail) {
				try {
					ControlMail.sendMailForPaymentExcursion(e);
				} catch (MessagingException err) {
				}
			}
		}
		final List<Member> birthday = this.birthday(DAYTOCHECK, people);
		map.put("Compleanni a breve",
				birthday.stream()
						.map(m -> m.getName() + " " + m.getSurname() + "[ " + m.getBirthday().toString() + " ]")
						.collect(Collectors.toList()));
		if (mail) {
			try {
				ControlMail.sendMailForBirthday(this.birthday(TODAY, people));
			} catch (MessagingException err) {
			}
		}
		if (this.checkDateIsBetween(unit.getLimitDateToPay(),
				unit.getLimitDateToPay().plus(-DAYTOCHECK, ChronoUnit.DAYS), LocalDate.now())) {
			map.put("Ragazzi che non hanno ancora pagato l'anno", unit.getMemberDidntPay().stream()
					.map(m -> m.getName() + " " + m.getSurname()).collect(Collectors.toList()));
			if (mail) {
				try {
					ControlMail.sendMailForTaxPayment(unit.getMemberDidntPay(), unit.getLimitDateToPay());
				} catch (MessagingException err) {
				}
			}
		}
		return map;
	}

	private boolean checkDateIsBetween(final LocalDate start, final LocalDate end, final LocalDate birthday) {

		final LocalDate tmp = Year.now().atMonth(birthday.getMonthValue()).atDay(birthday.getDayOfMonth());
		return tmp.compareTo(start) >= 0 && tmp.compareTo(end) <= 0;
	}

	private boolean checkMail(final Unit unit) {
		if (unit.getLastMailSend().equals(LocalDate.now())) {
			unit.setLastMailSend(LocalDate.now().plus(1, ChronoUnit.DAYS));
			return true;
		}
		return false;
	}

}
