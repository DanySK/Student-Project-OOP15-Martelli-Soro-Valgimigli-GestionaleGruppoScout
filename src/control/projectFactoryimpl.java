package control;

import java.time.LocalDate;
import java.util.Optional;

import control.myUtil.myOptional;
import model.*;

public class projectFactoryimpl implements projectFactory {
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	
	static public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita){
		try{
			Member prj_member = new Memberimpl(nome, cognome, dataNascita);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
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
			Member prj_member = new Memberimpl(String nome, String cognome, LocalDate dataNascita,
					myOptional<String> nomeTutor, myOptional<String> mailTutor, myOptional<String> telefonoTutor);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return this.prj_membro
	}
}
