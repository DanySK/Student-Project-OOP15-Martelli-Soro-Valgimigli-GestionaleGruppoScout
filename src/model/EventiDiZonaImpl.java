package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventiDiZonaImpl extends ExcursionImpl {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private List<String> altriReparti = new ArrayList<>();
	private List<Attivita> attivita = new ArrayList<>();

	public EventiDiZonaImpl(LocalDate dateStart, LocalDate dateEnd, Reparto reparto, String name,
			List<String> altriReparti) throws Exception {
		super(name, dateStart, reparto.getAllMember());
		this.reparto = reparto;
		this.setDateEnd(dateEnd);
		this.altriReparti = altriReparti;
	}

	public EventiDiZonaImpl(LocalDate dateStart, int durata, Reparto reparto, String name, List<String> altriReparti)
			throws Exception {
		super(name, dateStart, reparto.getAllMember());
		this.reparto = reparto;
		this.setDateEnd(dateStart.plusDays(durata));
		this.altriReparti = altriReparti;
	}
	
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	public List<Attivita> getAllAttivita() {
		return this.attivita;
	}

	public List<Attivita> getAllAttivitaInTime(LocalTime orario) {
		List<Attivita> tmp = new ArrayList<>();
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
	public void addAttivita (Attivita attivita){
		this.attivita.add(attivita);
	}
	public void addAttivita (String nomeAttivita,LocalTime orarioInizio){
		this.attivita.add(new AttivitaImpl (nomeAttivita,orarioInizio));
	}
	public void addAttivita (String nomeAttivita,LocalTime orarioInizio,LocalTime orarioFine){
		this.attivita.add(new AttivitaImpl (nomeAttivita,orarioInizio,orarioFine));
	}
	public void removeAttivita (Attivita attivita){
		this.attivita.remove(attivita);
	}
	public List<String> getOtherUnits() {
		return this.altriReparti;
	}

	public void addOtherUnit(String name) {
		this.altriReparti.add(name);
	}

	public void removeOtherUnit(String name) {
		this.altriReparti.remove(name);
	}

	public boolean containOtherUnit(String name) {
		return this.altriReparti.contains(name);
	}

	@Override
	protected void check(LocalDate dateStart, LocalDate dateEnd) throws Exception {
	}
}
