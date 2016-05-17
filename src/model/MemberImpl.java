package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberImpl extends Person implements Serializable,Member{
private String name;
private String surname;
//private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
private LocalDate birthday;
private List <String> competence;
private List <String> specialities;
//private Sentiero sentiero;
private Boolean promise;
private String totem;


public MemberImpl(String name,String surname,LocalDate birthday){
	super (name, surname, birthday);
	this.competence=new ArrayList<>();
	//this.sentiero=new Sentiero();
	this.specialities=new ArrayList<>();
	this.promise=false;
	this.totem=null;
}
public MemberImpl (String name,String surname,LocalDate birthday,Boolean promise,
		String totem,List<String> competence,List<String> specialities){
	super (name, surname, birthday);
	this.competence=new ArrayList<>(competence);
	this.specialities=new ArrayList <>(specialities);
	//this.sentiero=new Sentiero(liv,livello,fede,scuola,famiglia,relazioni);
	this.promise=promise;
	this.totem=totem;
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
	if (this.competence.contains(competence)){
		this.competence.remove(competence);
		return true;
	}
	return false;
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
    return ! this.totem.equals("NULL");
}
/**
 * 
 * @param totem
 */
public void setTotem(String totem){
    this.totem=totem;
}
public String getTotem (){
	return this.totem;
}
/**
 * 
 * @return
 */

}