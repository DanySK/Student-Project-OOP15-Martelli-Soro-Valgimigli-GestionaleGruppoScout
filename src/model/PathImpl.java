package model;

import java.io.Serializable;

import control.myUtil.myOptional;
import model.exception.IllegalOperationException;

public class PathImpl implements Serializable,model.Path{

	


private static final long serialVersionUID = 1L;
private String level;
private Integer liv;
private myOptional<String> school;
private myOptional<String> family;
private myOptional<String> relations;
private myOptional<String> faith;

public PathImpl (){
	this.liv=1;
	this.level="scoperta";
	this.faith=myOptional.empty();
	this.school=myOptional.empty();
	this.family=myOptional.empty();
	this.relations=myOptional.empty();
}
public PathImpl (Integer liv,String level,String faith,String school,String family,String relations){
	this.liv=liv;
	this.level=level;
	this.faith=myOptional.of(faith);
	this.school=myOptional.of(school);
	this.family=myOptional.of(family);
	this.relations=myOptional.of(relations);
}
public Integer getLiv(){
	return this.liv;
}
public String getLevel(){
	return this.level;
}
public String getSchool(){
	return this.school.get();
}
public void setSchool (String school){
	this.school=myOptional.of(school);
}
public String getFamily(){
	return this.family.get();
}
public void setFamily (String family){
	this.family=myOptional.of(family);
}
public String getRelations(){
	return this.relations.get();
}
public void setRelations (String relations){
	this.relations=myOptional.of(relations);
}
public String getFaith(){
	return this.faith.get();
}
public void setFaith (String faith){
	this.faith=myOptional.of(faith);
}
public void livUp() throws IllegalOperationException{
	if (this.liv==3){
		throw new IllegalOperationException();
		}
	this.liv=this.liv+1;
	updateLevel();
}
public void livDown() throws IllegalOperationException{/*return false if liv is 3*/
	if (this.liv==1){
		throw new IllegalOperationException();
	}
	this.liv=this.liv-1;
	updateLevel();
}

private void updateLevel(){
	if (this.liv==1) this.level="scoperta";
	if (this.liv==2) this.level="competenza";
	if (this.liv==3) this.level="responsabilità";
}
}
