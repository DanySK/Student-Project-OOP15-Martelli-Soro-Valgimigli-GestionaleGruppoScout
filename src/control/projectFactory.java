package control;

import java.time.LocalDate;

import control.myUtil.myOptional;
import model.ExcursionImpl;
import model.Member;
import model.Squadron;

public interface projectFactory {
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex);
	/**
	 * Returns a instance of class Member. This method can accept each possible parameter 
	 * 
	 * @param nome
	 * @param surname
	 * @param birthday
	 * @param nameTutor
	 * @param mailTutor
	 * @param phoneTutor
	 * @return
	 */
	public Member getMember(String name, String surname, LocalDate birthday, boolean sex,
			myOptional<String> nameTutor, myOptional<String> mailTutor, myOptional<Long> phoneTutor);
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param place
	 * @return
	 */
	public ExcursionImpl getGeneralExcursion(LocalDate dateStart, myOptional<LocalDate> dateEnd,
			myOptional<String> place);
	/**
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	public Squadron getSquadron(String name, Boolean sex);
	
}
