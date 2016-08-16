package model;

import java.time.LocalTime;

import control.myUtil.myOptional;

public class AttivitaImpl implements Attivita {
		private String name;
		private LocalTime orarioInizio;
		private myOptional<LocalTime> orarioFine;
		public AttivitaImpl (final String name,final LocalTime orarioInizio){
			this.name=name;
			this.orarioInizio=orarioInizio;
			this.orarioFine=myOptional.empty();
		}
		public AttivitaImpl (final String name,final LocalTime orarioInizio,final LocalTime orarioFine){
			this.name=name;
			this.orarioInizio=orarioInizio;
			this.orarioFine=myOptional.of(orarioFine);
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(final String name) {
			this.name = name;
		}
		
		public LocalTime getOrarioInizio() {
			return orarioInizio;
		}
		
		public void setOrarioInizio(final LocalTime orarioInizio) {
			this.orarioInizio = orarioInizio;
		}
		public boolean haOrarioFine (){
			return this.orarioFine.isPresent();
		}
		public LocalTime getOrarioFine(){
			return this.orarioFine.get();
		}
		public void setOrarioFine(final LocalTime orarioFine){
			this.orarioFine=myOptional.of(orarioFine);
		}
 }

