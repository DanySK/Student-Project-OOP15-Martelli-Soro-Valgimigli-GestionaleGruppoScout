package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.exception.IllegalDateException;

public class EventiDiZonaImpl extends ExcursionImpl implements EventiDiZona {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private final List<String> altriReparti;
	private final List<Attivita> attivita = new ArrayList<>();

	public EventiDiZonaImpl(final LocalDate dateStart,final  LocalDate dateEnd,final  Reparto reparto,final  String name,
			final List<String> altriReparti) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.reparto = reparto;
		this.setDateEnd(dateEnd);
		this.altriReparti = altriReparti;
	}

	public EventiDiZonaImpl(final LocalDate dateStart,final  int durata,final  Reparto reparto,final  String name,final  List<String> altriReparti)
			throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.reparto = reparto;
		this.setDateEnd(dateStart.plusDays(durata));
		this.altriReparti = altriReparti;
	}
	
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(final Reparto reparto) {
		this.reparto = reparto;
	}

	public List<Attivita> getAllAttivita() {
		return this.attivita;
	}

	public List<Attivita> getAllAttivitaInTime(final LocalTime orario) {
		final List<Attivita> tmp = new ArrayList<>();
		this.attivita.forEach(e -> {
			if (!e.haOrarioFine() && e.getOrarioInizio().equals(orario)) {
				tmp.add(e);
			} else if (e.haOrarioFine()) {
				if (e.getOrarioFine().isAfter(orario) && e.getOrarioInizio().isBefore(orario)) {
					tmp.add(e);
				}
			}
		});
		return tmp;
	}
	public void addAttivita (final Attivita attivita){
		this.attivita.add(attivita);
	}
	public void addAttivita (final String nomeAttivita,final LocalTime orarioInizio){
		this.attivita.add(new AttivitaImpl (nomeAttivita,orarioInizio));
	}
	public void addAttivita (final String nomeAttivita,final LocalTime orarioInizio,final LocalTime orarioFine){
		this.attivita.add(new AttivitaImpl (nomeAttivita,orarioInizio,orarioFine));
	}
	public void removeAttivita (final Attivita attivita){
		this.attivita.remove(attivita);
	}
	public List<String> getOtherUnits() {
		return this.altriReparti;
	}

	public void addOtherUnit(final String name) {
		this.altriReparti.add(name);
	}

	public void removeOtherUnit(final String name) {
		this.altriReparti.remove(name);
	}

	public boolean containOtherUnit(final String name) {
		return this.altriReparti.contains(name);
	}

	@Override
	protected void check(final LocalDate dateStart,final  LocalDate dateEnd) throws IllegalDateException {
	}
}
