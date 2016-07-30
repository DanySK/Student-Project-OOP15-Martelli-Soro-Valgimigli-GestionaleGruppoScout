package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.myOptional;
import model.Campo;
import model.CampoImpl;
import model.ExcursionImpl;
import model.Member;
import model.MemberImpl;
import model.Reparto;
import model.RepartoImpl;
import model.Squadron;
import model.SquadronImpl;
import model.Tutor;
import model.TutorImpl;
import model.Uscita;
import model.UscitaImpl;
import model.UscitaSquadriglia;
import model.UscitaSquadrigliaImpl;
import model.exception.IllegalDateException;
import view.general_utility.WarningNotice;

public class projectFactoryimpl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6423409525615896639L;
	/**
	 * Returns a Instance of class Member. This method wants only basic parameters
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @return
	 */
	public static Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex){
		Member prj_member = null;
		try{
			prj_member = new MemberImpl(nome, cognome, dataNascita, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		
		return prj_member;
	}
	/**
	 *  Returns a instance of class Member. This method can accept each possible parameter 
	 * @param name
	 * @param surname
	 * @param birthday
	 * @param sex
	 * @param nameTutor
	 * @param mailTutor
	 * @param phoneTutor
	 * @return
	 */
	public static Member getMember(String name, String surname, LocalDate birthday, boolean sex,
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
			prj_member = new MemberImpl(name, surname, birthday, sex, prj_tutor);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return prj_member;
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param place
	 * @return
	 */
	public static ExcursionImpl getGeneralExcursion(LocalDate dateStart, myOptional<LocalDate> dateEnd,
			myOptional<String> place) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	public static Squadron getSquadron(String name, Boolean sex) {
		Squadron sq = null;
		try{
			sq = new SquadronImpl(name, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return sq;
	}
	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param name
	 * @return
	 */
	public static Reparto getReparto(Member leaderM, Member leaderF, String name){
		return new RepartoImpl(leaderM, leaderF, new ArrayList<>(), name);
	}
	/**
	 * 
	 * @param capoMaschio
	 * @param capoFemmina
	 * @param aiutanti
	 * @param name
	 * @return
	 */
	public static Reparto getReparto(Member leaderM, Member leaderF, List<Member> helper,String name){
		return new RepartoImpl(leaderM, leaderF, helper, name);
	}
	/**
	 * For experiments and testing
	 * @param name
	 * @return
	 */
	public static Member getLeaderM(String name, String surname){
		return new MemberImpl(name, surname, LocalDate.now(), true);
	}
	/**
	 *  For experiments and testing
	 * @param name
	 * @param surname
	 * @return
	 */
	public static Member getLeaderF(String name, String surname){
		return new MemberImpl(name, surname, LocalDate.now(), false);
	}
	/**
	 * 
	 * @param dateStart
	 * @param reparto
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Uscita getStdExcursion(LocalDate dateStart,Reparto reparto,String name) throws Exception {
		try {
			return new UscitaImpl(dateStart, reparto, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param sq
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static UscitaSquadriglia getSqExcursion(LocalDate dateStart,int duration,Squadron sq,String name) throws Exception{
		try {
			return new UscitaSquadrigliaImpl(dateStart, duration, sq, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param sq
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static UscitaSquadriglia getSqExcursion(LocalDate dateStart,LocalDate dateEnd,Squadron sq,String name) throws Exception{
		try {
			return new UscitaSquadrigliaImpl(dateStart, dateEnd, sq, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param rp
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Campo getCamp(LocalDate dateStart,LocalDate dateEnd,Reparto rp,String name) throws Exception{
		try {
			return new CampoImpl(dateStart, dateEnd, rp, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param dateStart
	 * @param duration
	 * @param rp
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Campo getCamp(LocalDate dateStart,int duration,Reparto rp,String name) throws Exception{
		try {
			return new CampoImpl(dateStart, duration, rp, name);
		} catch (IllegalDateException e) {
			new WarningNotice(e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param rp
	 * @return
	 */
	public static Unit getUnit(Reparto rp){
		return new UnitImpl(rp);
	}
}
