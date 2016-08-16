package model;

import java.time.LocalTime;

import control.myUtil.myOptional;

public class AttivitaImpl implements Attivita {
		private String name;
		private LocalTime orarioInizio;
		private myOptional<LocalTime> orarioFine;
		public AttivitaImpl (String name,LocalTime orarioInizio){
			this.name=name;
			this.orarioInizio=orarioInizio;
			this.orarioFine=myOptional.empty();
		}
		public AttivitaImpl (String name,LocalTime orarioInizio,LocalTime orarioFine){
			this.name=name;
			this.orarioInizio=orarioInizio;
			this.orarioFine=myOptional.of(orarioFine);
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public LocalTime getOrarioInizio() {
			return orarioInizio;
		}
		
		public void setOrarioInizio(LocalTime orarioInizio) {
			this.orarioInizio = orarioInizio;
		}
		public boolean haOrarioFine (){
			return this.orarioFine.isPresent();
		}
		public LocalTime getOrarioFine(){
			return this.orarioFine.get();
		}
		public void setOrarioFine(LocalTime orarioFine){
			this.orarioFine=myOptional.of(orarioFine);
		}
 }

