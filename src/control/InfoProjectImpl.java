package control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import control.myUtil.Pair;
import model.Member;
import model.Squadron;

public class InfoProjectImpl implements InfoProject {


	@Override
	public String getSquadronGeneralInfo(String nameOfSquadron, Container cnt) {
		String info = "";
		Squadron sq =(Squadron) cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron));
		info += "Nome: "+ sq.getNome() + "\n";
		info += "Sesso: " + (sq.getSesso() ? "Maschi" : "Femmini")+ "\n";
		info += "Capo: " + sq.getCapo().getName()+ "\n";
		info += "Vice: " + sq.getVice().getName() + "\n";
		info += "Trice: "+ sq.getTrice().getName() + "\n";
		info += "Numero di membri: " + sq.getMembri().keySet().size();
		return info;
	}

	@Override
	public List<Pair<String, String>> getSquadronSpecificInfo(String nameOfSquadron, Container cnt) {
		List<Pair<String,String>> info = new ArrayList<>();
		Squadron sq =(Squadron) cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron));
		info.add(new Pair<>("Nome: ", sq.getNome()));
		info.add(new Pair<>("Sesso: ", (sq.getSesso() ? "Maschi" : "Femmini")));
		info.add(new Pair<>("Capo: ", sq.getCapo().getName()));
		info.add(new Pair<>("Vice: ", sq.getVice().getName()));
		info.add(new Pair<>("Trice: ", sq.getTrice().getName()));
		info.add(new Pair<>("Numero di membri: ", Integer.toString(sq.getMembri().keySet().size())));
		return info;
	}

	@Override
	public List<Member> getMemberOfSquadron(String nameOfSquadron, Container cnt) {
		Squadron sq = (Squadron) cnt.getSquadrons().stream().filter(e -> e.getNome().equals(nameOfSquadron));
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
		info.add(new Pair<>("Eta", "Default"));
		info.add(new Pair<>("Promessa", (member.getPromise()? "Fatta":"Da fare")));
		if(! member.hasTotem()){
			info.add(new Pair<>("Totem", member.getTotem()));
		}else{
			info.add(new Pair<>("Totem", ""));
		}
		info.add(new Pair<>("Data di nascita", member.getBirthday().toString()));
		return info;
	}

}
