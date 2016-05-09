package control;

import java.time.LocalDate;
import java.util.Optional;

public class projectFactoryimpl implements projectFactory {
	private Member prj_member = null;
	private Squadron prj_squadron = null;
	private Excursion prj_excursion = null;
	private Tax prj_tax = null;
	//private Sentiero prj_sentiero = null;
	private Target prj_target = null;
	
	static public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita){
		try{
			this.prj_member = new Memberimpl(nome, cognome, dataNascita);
		}catch(ErrorMemberException e){
			// deve fare qualcosa
		}
		
		return this.prj_membro;
	}
	static public Member getMember(String nome, String cognome, LocalDate dataNascita,
			Optional<String> nomeTutor, Optional<String> mailTutor, Optional<String> telefonoTutor){
}
