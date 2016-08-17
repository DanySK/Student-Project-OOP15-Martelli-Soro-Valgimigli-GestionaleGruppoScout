package model;

import java.io.Serializable;

import control.myUtil.MyOptional;
import model.exception.IllegalOperationException;

public class PathImpl implements Serializable,model.Path{

	

private static final String DEFAULT_VOID_OPTIONAL="Non settato";
private static final int SCOPERTA=1;
private static final int COMPETENZA=2;
private static final int RESPONSABILITA=3;
private static final long serialVersionUID = 1L;
private String level;
private Integer liv;
private MyOptional<String> school;
private MyOptional<String> family;
private MyOptional<String> relations;
private MyOptional<String> faith;

public PathImpl (){
	this.liv=1;
	this.level="scoperta";
	this.faith=MyOptional.empty();
	this.school=MyOptional.empty();
	this.family=MyOptional.empty();
	this.relations=MyOptional.empty();
}
public PathImpl (final Integer liv,final String level,final String faith,final String school,final String family,final String relations){
	this.liv=liv;
	this.level=level;
	this.faith=MyOptional.of(faith);
	this.school=MyOptional.of(school);
	this.family=MyOptional.of(family);
	this.relations=MyOptional.of(relations);
}
public Integer getLiv(){
	return this.liv;
}
public String getLevel(){
	return this.level;
}
public String getSchool(){
	return this.school.orElse(DEFAULT_VOID_OPTIONAL);
}
public void setSchool (final String school){
	this.school=MyOptional.of(school);
}
public String getFamily(){
	return this.family.orElse(DEFAULT_VOID_OPTIONAL);
}
public void setFamily (final String family){
	this.family=MyOptional.of(family);
}
public String getRelations(){
	return this.relations.orElse(DEFAULT_VOID_OPTIONAL);
}
public void setRelations (final String relations){
	this.relations=MyOptional.of(relations);
}
public String getFaith(){
	return this.faith.orElse(DEFAULT_VOID_OPTIONAL);
}
public void setFaith (final String faith){
	this.faith=MyOptional.of(faith);
}
public void livUp() throws IllegalOperationException{
	if (this.liv==RESPONSABILITA){
		throw new IllegalOperationException();
		}
	this.liv=this.liv+1;
	updateLevel();
}
public void livDown() throws IllegalOperationException{
	if (this.liv==SCOPERTA){
		throw new IllegalOperationException();
	}
	this.liv=this.liv-1;
	updateLevel();
}

private void updateLevel(){
	if (this.liv==SCOPERTA){
		this.level="scoperta";
	}
	if (this.liv==COMPETENZA){
		this.level="competenza";
	}
	if (this.liv==RESPONSABILITA){
		this.level="responsabilità";
	}
}
}
