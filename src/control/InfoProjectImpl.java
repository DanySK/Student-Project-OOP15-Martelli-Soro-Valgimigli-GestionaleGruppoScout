package control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import control.myUtil.Pair;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.Squadron;
import model.Uscita;
import model.UscitaSquadriglia;

public class InfoProjectImpl implements InfoProject {


	@Override
	public String getSquadronGeneralInfo(String nameOfSquadron, Container cnt) {
		String info = "";
		Squadron sq =cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron))
												  .findFirst()
												  .get();
		info += "Nome: "+ sq.getNome() + "\n";
		info += "Sesso: " + (sq.getSesso() ? "Maschi" : "Femmine")+ "\n";
		if(sq.isCapoPresent())
		info += "Capo: " + sq.getCapo().getName()+ " " + sq.getCapo().getSurname()+ "\n";
		if(sq.isVicecapoPresent())
		info += "Vice: " + sq.getVice().getName() +" " + sq.getVice().getSurname()+ "\n";
		if(sq.isTricecapoPresent())
		info += "Trice: "+ sq.getTrice().getName()+ " " + sq.getTrice().getSurname()+ "\n";
		info += "Numero di membri: " + sq.getMembri().keySet().size();
		return info;
	}

	@Override
	public List<Pair<String, String>> getSquadronSpecificInfo(String nameOfSquadron, Container cnt) {
		List<Pair<String,String>> info = new ArrayList<>();
		Squadron sq = cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron))
										.findFirst()
										.get();
		info.add(new Pair<>("Nome: ", sq.getNome()));
		info.add(new Pair<>("Sesso: ", (sq.getSesso() ? "Maschi" : "Femmini")));
		if(sq.isCapoPresent())
		info.add(new Pair<>("Capo: ", sq.getCapo().getName()+ " " + sq.getCapo().getSurname()));
		if(sq.isVicecapoPresent())
		info.add(new Pair<>("Vice: ",sq.getVice().getName() +" " + sq.getVice().getSurname()));
		if(sq.isTricecapoPresent())
		info.add(new Pair<>("Trice: ",  sq.getTrice().getName()+ " " + sq.getTrice().getSurname()));
		info.add(new Pair<>("Numero di membri: ", Integer.toString(sq.getMembri().keySet().size())));
		return info;
	}

	@Override
	public List<Member> getMemberOfSquadron(String nameOfSquadron, Container cnt) {
		Squadron sq = cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron)).findFirst().get();
		return sq.getMembri().keySet()
							 .stream()
							 .collect(Collectors.toList());
	}
		
										  

	@Override
	public List<Pair<String, String>> getMemberSpecificalInfo(Member member) {
		List<Pair<String,String>> info = new ArrayList<>();
		info.add(new Pair<>("Nome", member.getName()));
		info.add(new Pair<>("Cognome", member.getSurname()));
		info.add(new Pair<>("Sesso", (member.getSex() ? "Maschio":"Femmina")));
		info.add(new Pair<>("Eta", member.getHowIsHold().toString()));
		info.add(new Pair<>("Promessa", (member.getPromise()? "Fatta":"Da fare")));
		if(! member.hasTotem()){
			info.add(new Pair<>("Totem", member.getTotem()));
		}else{
			info.add(new Pair<>("Totem", ""));
		}
		info.add(new Pair<>("Data di nascita", member.getBirthday().toString()));
		return info;
	}

	@Override
	public String getExcursionInfo(Excursion e) {
		String info = "";
		info += "Nome uscita: \t" + e.getName();
		info += "\nDove: \t" + e.getPlace();
		info += "\nPrezzo: \t" + e.getPrize();
		info += "\n Partecipanti: " + e.getAllPartecipanti().stream()
															.map(s -> s.getName() + " " + s.getSurname())
															.collect(Collectors.joining());
		info += "\nNon hanno pagato: " + e.getNonPaganti().stream()
										.map(s -> s.getName() + " " + s.getSurname())
										.collect(Collectors.joining());
		if(e instanceof Uscita){
			info = "\nTipologia: \t Uscita di reparto"; 
		}
		if(e instanceof UscitaSquadriglia){
			info = "\nTipologia: \t Uscita di suqdriglia ( " + 
						((UscitaSquadriglia)e).getSquadriglia().getNome() + " )"; 
		}
		if(e instanceof Gemellaggi){
			info = "\nTipologia: \t Gemellaggio (" + ((Gemellaggi) e).getOtherUnits()
																   .stream()
																   .collect(Collectors.joining()) + ")";
		}
		if(e instanceof EventiDiZona){
			info = "\nTipologia: \t Evento di Zona"; 
		}
		if(e instanceof Campo){
			info = "\nTipologia: \t Campo"; 
		}
		return info;
	}

}
