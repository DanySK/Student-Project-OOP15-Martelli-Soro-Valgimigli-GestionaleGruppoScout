package control;

import java.time.LocalDate;
import java.util.Optional;

import control.myUtil.myOptional;
import model.*;
import view.general_utility.WarningNotice;

public class projectFactoryimpl implements projectFactory {
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	
	static public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex){
		Member prj_member = null;
		try{
			prj_member = new MemberImpl(nome, cognome, dataNascita,sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		
		return prj_member;
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
