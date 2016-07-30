package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.Reparto;
import model.Roles;
import model.Squadron;
import model.Uscita;
import model.UscitaSquadriglia;
import view.general_utility.WarningNotice;

public class UnitImpl implements Unit, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4391311361473527351L;
	private String nameToSave;
	private Container container;
	private Reparto rep;
	private List<Excursion> excursions;
	
	public UnitImpl(Reparto rep){
		this.nameToSave = rep.getName().replace(' ','_');
		this.excursions = new ArrayList<>();
		this.rep = rep;
	}
	@Override
	public String getName() {
		return this.nameToSave;
	}

	@Override
	public Container getContainers() {
		this.container = new ContainerImpl(this.rep.getAllSquadron(),
				this.rep.getMembriSenzaSquadriglia(),this.excursions);
		return this.container;
	}

	@Override
	public void setName(String name) {
		this.rep.setName(name);
	}

	@Override
	public String info(){
		String info = "";
		info += "Nome reparto: "+ this.rep.getName() + "\n";
		info += "Capo maschio: "+ this.rep.getCapoM().getName() + "\n";
		info += "Capo femmina: "+ this.rep.getCapoF().getName() + "\n";
		info += "Number of members: " + this.getContainers().getMembers().size() + "\n";
		info += "Number of squadrons: " + this.getContainers().getSquadrons().size() + "\n";
		return info;
	}
	
	@Override
	public List<Pair<String, String>> getUnitSpecificInfo() {
		List<Pair<String, String>> info = new ArrayList<>();
		
		info.add(new Pair<>("Name", this.getName()));
		info.add(new Pair<>("Number of member", Integer.toString(this.getContainers().getMembers().size())));
		info.add(new Pair<>("Number of squadron", Integer.toString(this.getContainers().getSquadrons().size())));
		info.add(new Pair<>("Number of boys", Integer.toString(this.getContainers().members(e -> e.getSex())
																				   .size())));
		info.add(new Pair<>("Number of girls", Integer.toString(this.getContainers().members(e -> ! e.getSex())
																				   .size())));
		return info;
	}
	
	
	public void addUscita(Uscita exc) {
		// controllo date
		// controllo membri inseriti
		this.excursions.add(exc);
	}
	@Override
	public void addMember(Member m) {
		try{
			this.rep.addMembroSenzaSquadriglia(m);
		}catch(Exception e){
			e.printStackTrace();
			new WarningNotice(e.getMessage());
		}
		
	}
	@Override
	public void createSq(Squadron sq) {
		try{
			this.rep.addSquadron(sq);
		}catch(Exception e){
			e.printStackTrace();
			new WarningNotice(e.getMessage());
		}
	}
	@Override
	public void removeMember(Member m) {
		try{
			if(this.rep.getMembriSenzaSquadriglia().contains(m)){
				this.rep.removeMembroSenzaSquadriglia(m);
			}
		}catch(Exception e){
			e.printStackTrace();
			new WarningNotice(e.getMessage());
		}
	}
	@Override
	public void removeSq(Squadron sq) {
		try{
			this.rep.removeSquadron(sq);
		}catch(Exception e){
			e.printStackTrace();
			new WarningNotice(e.getMessage());
		}
	}
	@Override
	public void putMemberInSq(Member m, Squadron sq, Roles rl) {
		try{
			this.rep.spostaMembroInSquadriglia(m, rl, sq);
		}catch(Exception e){
			e.printStackTrace();
			new WarningNotice(e.getMessage());
		}
	}
	@Override
	public void changeMemberFromSq(Member m, Squadron sqNew, Roles rl) {
		try{
			if( this.rep.getMembriSenzaSquadriglia().contains(m) ){
				new WarningNotice("Il ragazzo non appartiene a nessuna squadriglia \n verrà comunque assegnato");
			}else{
				this.rep.removeMemberFromSquadron(m);
			}
			
			this.putMemberInSq(m, sqNew, rl );
			
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
	}
	@Override
	public LocalDate getLimitDateToPay() {
		return this.rep.getDateToPay();
	}
	@Override
	public List<Member> getMemberDidntPay() {
		return this.rep.getMembersNotPaid(Year.now().getValue());
	}
	@Override
	public void addExcursion(Excursion exc) {
		this.excursions.add(exc);
	}
	@Override
	public Reparto getReparto() {
		return this.rep;
	}
	

}
