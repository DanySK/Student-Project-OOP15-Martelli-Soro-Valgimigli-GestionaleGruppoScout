package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import control.myUtil.Pair;
import model.escursioni.Campo;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.escursioni.Uscita;
import model.escursioni.UscitaSquadriglia;
import model.reparto.Member;
import model.reparto.Squadron;

public class InfoProjectImpl implements InfoProject {

	@Override
	public String getSquadronGeneralInfo(final String nameOfSquadron, final Container cnt) {
		String info = "";
		final Squadron sq = cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron)).findFirst()
				.get();
		info += "Nome: " + sq.getNome() + "\n";
		info += "Sesso: " + (sq.getSesso() ? "Maschi" : "Femmine") + "\n";
		if (sq.isCapoPresent()) {
			info += "Capo: " + sq.getCapo().getName() + " " + sq.getCapo().getSurname() + "\n";
		}
		if (sq.isVicecapoPresent()) {
			info += "Vice: " + sq.getVice().getName() + " " + sq.getVice().getSurname() + "\n";
		}
		if (sq.isTricecapoPresent()) {
			info += "Trice: " + sq.getTrice().getName() + " " + sq.getTrice().getSurname() + "\n";
		}
		info += "Numero di membri: " + sq.getMembri().keySet().size();
		return info;
	}

	@Override
	public List<Pair<String, String>> getSquadronSpecificInfo(final String nameOfSquadron, final Container cnt) {
		final List<Pair<String, String>> info = new ArrayList<>();
		final Squadron sq = cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron)).findFirst()
				.get();
		info.add(new Pair<>("Nome: ", sq.getNome()));
		info.add(new Pair<>("Sesso: ", (sq.getSesso() ? "Maschi" : "Femmine")));
		if (sq.isCapoPresent()) {
			info.add(new Pair<>("Capo: ", sq.getCapo().getName() + " " + sq.getCapo().getSurname()));
		}
		if (sq.isVicecapoPresent()) {
			info.add(new Pair<>("Vice: ", sq.getVice().getName() + " " + sq.getVice().getSurname()));
		}
		if (sq.isTricecapoPresent()) {
			info.add(new Pair<>("Trice: ", sq.getTrice().getName() + " " + sq.getTrice().getSurname()));
		}
		info.add(new Pair<>("Numero di membri: ", Integer.toString(sq.getMembri().keySet().size())));
		return info;
	}

	@Override
	public List<Member> getMemberOfSquadron(final String nameOfSquadron, final Container cnt) {
		final Squadron sq = cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron)).findFirst()
				.get();
		return sq.getMembri().keySet().stream().collect(Collectors.toList());
	}

	@Override
	public List<Pair<String, String>> getMemberSpecificalInfo(final Member member) {
		final List<Pair<String, String>> info = new ArrayList<>();
		info.add(new Pair<>("Nome", member.getName()));
		info.add(new Pair<>("Cognome", member.getSurname()));
		info.add(new Pair<>("Sesso", (member.getSex() ? "Maschio" : "Femmina")));
		info.add(new Pair<>("Eta", Integer.toString(member.getHowIsHold().getYears())));
		info.add(new Pair<>("Promessa", (member.getPromise() ? "Fatta" : "Da fare")));
		if (!member.hasTotem()) {
			info.add(new Pair<>("Totem", "-"));
		} else {
			info.add(new Pair<>("Totem", member.getTotem()));
		}
		info.add(new Pair<>("Data di nascita", member.getBirthday().toString()));
		return info;
	}

	@Override
	public String getExcursionInfo(final Excursion e) {
		String info = "";
		info += "Nome uscita: \t" + e.getName() + System.lineSeparator();
		info += "Dove: \t" + e.getPlace() + System.lineSeparator();
		info += "Prezzo: \t" + e.getPrize() + System.lineSeparator();
		info += " Partecipanti: " + e.getAllPartecipants().stream().map(s -> s.getName() + " " + s.getSurname())
				.collect(Collectors.joining());
		info += System.lineSeparator() + "Non hanno pagato: "
				+ e.getNotPaied().stream().map(s -> s.getName() + " " + s.getSurname()).collect(Collectors.joining());
		if (e instanceof Uscita) {
			info = System.lineSeparator() + "Tipologia: \t Uscita di reparto";
		}
		if (e instanceof UscitaSquadriglia) {
			info = System.lineSeparator() + "Tipologia: \t Uscita di suqdriglia ( "
					+ ((UscitaSquadriglia) e).getSquadriglia().getNome() + " )";
		}
		if (e instanceof Gemellaggi) {
			info = System.lineSeparator() + "Tipologia: \t Gemellaggio ("
					+ ((Gemellaggi) e).getOtherUnits().stream().collect(Collectors.joining()) + ")";
		}
		if (e instanceof EventiDiZona) {
			info = System.lineSeparator() + "\nTipologia: \t Evento di Zona";
		}
		if (e instanceof Campo) {
			info = System.lineSeparator() + "Tipologia: \t Campo";
		}

		info += System.lineSeparator() + "Da: " + e.getDateStart().toString() + "  fino a: "
				+ e.getDateEnd().toString();

		return info;
	}

	@Override
	public Map<String, List<String>> getExcursionSpacificalInfo(final Excursion e) {
		final Map<String, List<String>> info = new HashMap<>();
		List<String> value = new ArrayList<>();
		value.add(e.getName());
		value.add(e.getPlace());
		value.add(e.getPrize().toString());
		value.add(e.getDateStart().toString());
		value.add(e.getDateEnd().toString());
		info.put("Nome", value.subList(0, 1));
		info.put("Dove", value.subList(1, 2));
		info.put("Prezzo", value.subList(2, 3));
		info.put("Data", value.subList(3, 4));
		info.put("DataFine", value.subList(4, 5));
		info.put("Partecipanti", e.getAllPartecipants().stream().map(s -> s.getName() + " " + s.getSurname())
				.collect(Collectors.toList()));
		info.put("NonPaganti",
				e.getNotPaied().stream().map(s -> s.getName() + " " + s.getSurname()).collect(Collectors.toList()));

		if (e instanceof Uscita) {
			value = new ArrayList<>();
			value.add("Uscita");
			info.put("Tipologia", value);
		}
		if (e instanceof UscitaSquadriglia) {
			value = new ArrayList<>();
			value.add("Uscita di Squadriglia ");
			value.add(((UscitaSquadriglia) e).getSquadriglia().getNome());
			info.put("Tipologia", value.subList(0, 1));
			info.put("Squadriglie", value.subList(1, 2));

		}
		if (e instanceof Gemellaggi) {
			value = new ArrayList<>();
			value.add("Gemellaggio");

			info.put("Tipologia", value);
			info.put("Reparti", ((Gemellaggi) e).getOtherUnits());
		}
		if (e instanceof EventiDiZona) {
			value = new ArrayList<>();
			value.add("Evento di Zona");

			info.put("Tipologia", value);
		}
		if (e instanceof Campo) {
			value = new ArrayList<>();
			value.add("Campo");

			info.put("Tipologia", value);
		}
		return info;
	}

}
