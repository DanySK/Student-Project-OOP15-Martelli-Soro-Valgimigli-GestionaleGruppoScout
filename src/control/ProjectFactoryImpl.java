package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.exception.MemberSexException;
import control.myUtil.MyOptional;
import model.escursioni.Campo;
import model.escursioni.CampoImpl;
import model.escursioni.EventiDiZona;
import model.escursioni.EventiDiZonaImpl;
import model.escursioni.ExcursionImpl;
import model.escursioni.Gemellaggi;
import model.escursioni.GemellaggiImpl;
import model.escursioni.Uscita;
import model.escursioni.UscitaImpl;
import model.escursioni.UscitaSquadriglia;
import model.escursioni.UscitaSquadrigliaImpl;
import model.exception.IllegalDateException;
import model.exception.IllegalPhoneNumberException;
import model.exception.IllegalYearsException;
import model.reparto.Capo;
import model.reparto.CapoImpl;
import model.reparto.Member;
import model.reparto.MemberImpl;
import model.reparto.Reparto;
import model.reparto.RepartoImpl;
import model.reparto.Squadron;
import model.reparto.SquadronImpl;
import model.reparto.Tutor;
import model.reparto.TutorImpl;

public final class ProjectFactoryImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6423409525615896639L;

	private ProjectFactoryImpl() {
	};

	/**
	 * Returns a Instance of class Member. This method wants only basic
	 * parameters
	 * 
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 * @throws IllegalYearsException
	 */
	public static Member getSimpleMember(final String nome, final String cognome, final LocalDate dataNascita,
			final boolean sex) throws IllegalYearsException {
		return new MemberImpl(nome, cognome, dataNascita, sex);
	}

	/**
	 * Returns a instance of class Member. This method can accept each possible
	 * parameter
	 * 
	 * @param name
	 * @param surname
	 * @param birthday
	 * @param sex
	 * @param nameTutor
	 * @param mailTutor
	 * @param phoneTutor
	 * @return
	 * @throws IllegalYearsException
	 */
	public static Member getMember(final String name, final String surname, final LocalDate birthday, final boolean sex,
			final MyOptional<String> nameTutor, final MyOptional<String> mailTutor, final MyOptional<Long> phoneTutor)
			throws IllegalYearsException {
		Member prjMember = null;
		Tutor prjTutor = null;
		prjTutor = new TutorImpl();
		if (mailTutor.isPresent()) {
			prjTutor.setEmail(mailTutor.get());
		}
		if (nameTutor.isPresent()) {
			prjTutor.setName(nameTutor.get());
		}
		if (phoneTutor.isPresent()) {
			prjTutor.setPhone(phoneTutor.get());
		}
		prjMember = new MemberImpl(name, surname, birthday, sex, prjTutor);
		return prjMember;
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param place
	 * @return A general excurision
	 */
	public static ExcursionImpl getGeneralExcursion(final LocalDate dateStart, final MyOptional<LocalDate> dateEnd,
			final MyOptional<String> place) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param name
	 * @param sex
	 * @return a empty Squadron 
	 */
	public static Squadron getSquadron(final String name, final Boolean sex) {
		return new SquadronImpl(name, sex);
	}

	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param name
	 * @return A reparto with leaders setted
	 * @throws MemberSexException
	 */

	public static Reparto getReparto(final Capo leaderM, final Capo leaderF, final String name)
			throws MemberSexException {
		return new RepartoImpl(leaderM, leaderF, new ArrayList<>(), name);
	}

	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param aiutanti
	 * @param name
	 * @return A reparto with leaders and halpers setted
	 * @throws MemberSexException
	 */

	public static Reparto getReparto(final Capo leaderM, final Capo leaderF, final List<Capo> helper, final String name)
			throws MemberSexException {
		return new RepartoImpl(leaderM, leaderF, helper, name);
	}

	/**
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param number
	 * @return A leader male setted 
	 * @throws IllegalPhoneNumberException
	 */
	public static Capo getLeaderM(final String name, final String surname, final LocalDate birthDay,
			final String number) throws IllegalPhoneNumberException {
		
		return new CapoImpl(name, surname, birthDay, true, number);
	}

	/**
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param number
	 * @return A leader Female setted
	 * @throws IllegalPhoneNumberException
	 */
	public static Capo getLeaderF(final String name, final String surname, final LocalDate birthDay,
			final String number) throws IllegalPhoneNumberException {
		return new CapoImpl(name, surname, birthDay, false, number);
	}

	/**
	 * 
	 * @param dateStart
	 * @param reparto
	 * @param name
	 * @return A Exit
	 * @throws IllegalDateException
	 * @throws Exception
	 */
	public static Uscita getStdExcursion(final LocalDate dateStart, final Reparto reparto, final String name)
			throws IllegalDateException {
		return new UscitaImpl(dateStart, reparto, name);
	}

	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param sq
	 * @param name
	 * @return Exit of a Squadron
	 * @throws IllegalDateException
	 * @throws Exception
	 */
	public static UscitaSquadriglia getSqExcursion(final LocalDate dateStart, final int duration, final Squadron sq,
			final String name) throws IllegalDateException {
		return new UscitaSquadrigliaImpl(dateStart, duration, sq, name);
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param sq
	 * @param name
	 * @return Exit of a squadron
	 * @throws IllegalDateException
	 * @throws Exception
	 */
	public static UscitaSquadriglia getSqExcursion(final LocalDate dateStart, final LocalDate dateEnd,
			final Squadron sq, final String name) throws IllegalDateException {
		return new UscitaSquadrigliaImpl(dateStart, dateEnd, sq, name);
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param rp
	 * @param name
	 * @return Camp
	 * @throws IllegalDateException
	 * @throws Exception
	 */
	public static Campo getCamp(final LocalDate dateStart, final LocalDate dateEnd, final Reparto rp, final String name)
			throws IllegalDateException {
		return new CampoImpl(dateStart, dateEnd, rp, name);
	}

	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param rp
	 * @param name
	 * @return Camp
	 * @throws IllegalDateException
	 * @throws Exception
	 */
	public static Campo getCamp(final LocalDate dateStart, final int duration, final Reparto rp, final String name)
			throws IllegalDateException {
		return new CampoImpl(dateStart, duration, rp, name);
	}

	/**
	 * 
	 * @param rp
	 * @return Unit
	 */
	public static Unit getUnit(final Reparto rp) {
		return new UnitImpl(rp);
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param reparto
	 * @param name
	 * @param altriReparti
	 * @return Local Event
	 * @throws IllegalDateException
	 */
	public static EventiDiZona getLocalEvent(final LocalDate dateStart, final LocalDate dateEnd, final Reparto reparto,
			final String name, final List<String> others) throws IllegalDateException {
		return new EventiDiZonaImpl(dateStart, dateEnd, reparto, name, others);
	}

	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param reparto
	 * @param name
	 * @param others
	 * @return Local Event
	 * @throws IllegalDateException
	 */
	public static EventiDiZona getLocalEvent(final LocalDate dateStart, final int duration, final Reparto reparto,
			final String name, final List<String> others) throws IllegalDateException {
		return new EventiDiZonaImpl(dateStart, duration, reparto, name, others);
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param reparto
	 * @param name
	 * @param others
	 * @return Two (or more) unit event
	 * @throws IllegalDateException
	 */
	public static Gemellaggi getEventTwoUnit(final LocalDate dateStart, final LocalDate dateEnd, final Reparto reparto,
			final String name, final List<String> others) throws IllegalDateException {
		return new GemellaggiImpl(dateStart, dateEnd, reparto, name, others);
	}

	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param reparto
	 * @param name
	 * @param others
	 * @return Two (or more) unit event
	 * @throws IllegalDateException
	 */
	public static Gemellaggi getEventMoreUnit(final LocalDate dateStart, final int duration, final Reparto reparto,
			final String name, final List<String> others) throws IllegalDateException {
		return new GemellaggiImpl(dateStart, duration, reparto, name, others);

	}
}