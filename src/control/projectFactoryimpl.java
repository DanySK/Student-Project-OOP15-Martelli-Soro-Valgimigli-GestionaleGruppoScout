package control;

import java.io.Serializable;
import java.time.LocalDate;

import control.myUtil.myOptional;
import model.ExcursionImpl;
import model.Member;
import model.MemberImpl;
import model.Squadron;
import model.SquadronImpl;
import model.Tutor;
import model.TutorImpl;
import view.general_utility.WarningNotice;

public class projectFactoryimpl implements projectFactory, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6423409525615896639L;
	@Override
	public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex){
		Member prj_member = null;
		try{
			prj_member = new MemberImpl(nome, cognome, dataNascita, sex, 0);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		
		return prj_member;
	}
	@Override
	public Member getMember(String name, String surname, LocalDate birthday, boolean sex,
			myOptional<String> nameTutor, myOptional<String> mailTutor, myOptional<Long> phoneTutor){
		Member prj_member = null;
		Tutor prj_tutor = null;
		try{
			prj_tutor = new TutorImpl();
			if(mailTutor.isPresent()){
				prj_tutor.setEmail(mailTutor.get());
			}
			if(nameTutor.isPresent()){
				prj_tutor.setName(nameTutor.get());
			}
			if(phoneTutor.isPresent()){
				prj_tutor.setPhone(phoneTutor.get());
			}
			prj_member = new MemberImpl(name, surname, birthday, sex, prj_tutor, 0);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return prj_member;
	}
	@Override
	public ExcursionImpl getGeneralExcursion(LocalDate dateStart, myOptional<LocalDate> dateEnd,
			myOptional<String> place) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Squadron getSquadron(String name, Boolean sex) {
		Squadron sq = null;
		try{
			sq = new SquadronImpl(name, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return sq;
	}
}
