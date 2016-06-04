package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.myOptional;

public class MemberImpl extends PersonImpl implements Serializable,Member,Person{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private List <String> competence;
private List <String> specialities;
//private Sentiero sentiero;
private Boolean promise;
private myOptional<String> totem;


public MemberImpl(String name,String surname,LocalDate birthday,Boolean sex){
	super (name, surname, birthday,sex);
	this.competence=new ArrayList<>();
	//this.sentiero=new Sentiero();
	this.specialities=new ArrayList<>();
	this.promise=false;
	this.totem= myOptional.empty();
}
public MemberImpl (String name,String surname,LocalDate birthday,Boolean sex,Boolean promise,
		String totem,List<String> competence,List<String> specialities){
	super (name, surname, birthday,sex);
	this.competence=new ArrayList<>(competence);
	this.specialities=new ArrayList <>(specialities);
	//this.sentiero=new Sentiero(liv,livello,fede,scuola,famiglia,relazioni);
	this.promise=promise;
	this.totem=myOptional.of(totem);
}

//public Sentiero getSentiero (){
//	return this.sentiero;
//}

public boolean addCompetence (String competence){/*return false if the competent is already contained*/
	if (this.competence.contains(competence)){
		return false;
	}
	this.competence.add(competence);
	return true;
}

public List<String> getCompetence(){
	return this.competence;
}
public List<String> getSpecialities(){
    return this.specialities;
}
/**
 * 
 * @param specialities
 * @return
 */
public boolean removeSpecialities (String specialities){/*return false if the competent is not already contained*/
    if (this.specialities.contains(specialities)){
        this.specialities.remove(specialities);
        return true;
    }
    return false;
}
public boolean addSpecialities(String specialities){
    if(!this.containsSpecialities(specialities)){
        this.specialities.add(specialities);
        return true;
    }else{
        return false;
    }
}
public boolean containsSpecialities (String specialities){/*return true if the competent is contained*/
    return this.specialities.contains(specialities);
}
public boolean removeCompetence (String competence){/*return false if the competent is not already contained*/
	
		return this.competence.remove(competence);
		
	
}
public boolean containsCompetence (String competence){/*return true if the competent is contained*/
	return this.competence.contains(competence);
}
public boolean getPromise(){
    return this.promise;
}
/**
 * 
 * @param promessa
 */
public void setPromise(boolean promessa){
    this.promise=promessa;
}
public boolean hasTotem(){
    return ! this.totem.equals(myOptional.empty());
}
/**
 * 
 * @param totem
 */
public void setTotem(String totem){
    this.totem=myOptional.of(totem);
}
public String getTotem (){
	
	return this.totem.get();
}




/**
 * 
 * @return
 */

}