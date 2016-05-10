package control;

import java.time.LocalDate;
import java.util.Optional;

import control.myUtil.myOptional;

public class projectFactoryimpl implements projectFactory {
	private Member prj_member = null;
	private Squadron prj_squadron = null;
	private Excursion prj_excursion = null;
	private Tax prj_tax = null;
	//private Sentiero prj_sentiero = null;
	private Target prj_target = null;
	
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	
	static public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita){
		try{
			this.prj_member = new Memberimpl(nome, cognome, dataNascita);
		}catch(ErrorMemberException e){
			// deve fare qualcosa
		}
		
		return this.prj_membro;
	}
	/**
	 * Returns a instance of class Member. This method can accept each possible parameter 
	 * 
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param nomeTutor
	 * @param mailTutor
	 * @param telefonoTutor
	 * @return
	 */
	static public Member getMember(String nome, String cognome, LocalDate dataNascita,
			myOptional<String> nomeTutor, myOptional<String> mailTutor, myOptional<String> telefonoTutor){
		try{
			this.prj_member = new Memberimpl(String nome, String cognome, LocalDate dataNascita,
					myOptional<String> nomeTutor, myOptional<String> mailTutor, myOptional<String> telefonoTutor);
		}catch(Exception e){
			// deve fare qualcosa
		}
		return this.prj_membro
	}
}
