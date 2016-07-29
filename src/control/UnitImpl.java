package control;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import control.exception.EntityAlreadyExistsException;
import control.myUtil.Pair;
import model.Member;
import model.Reparto;

public class UnitImpl implements Unit, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameToSave;
	private Container container;
	private Reparto rep;
	
	public UnitImpl(Member leaderM, Member leaderF, String nameOfRep, List<Member> helper){
		this.nameToSave = nameOfRep.replace(' ','_');
	}
	@Override
	public String getName() {
		return this.nameToSave;
	}

	@Override
	public Container getContainers() {
		this.container = new ContainerImpl(this.rep.getAllSquadron(), this.rep.getAllMember());
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setContainer(Container cnt) {
		this.container = cnt;
	}
	@Override
	public String info(){
		String info = "";
		info += "Unit Name: "+ this.getName() + "\n";
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
	
	public static void main(String[] s){
		Unit unit = new UnitImpl("Rosa");
		
		Container cnt = unit.getContainers();
		try {
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lorenzo", "Valgimigli", Year.of(1995).atMonth(12).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Andrea", "Rossi", Year.of(1995).atMonth(2).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lia", "Rossi", Year.of(1995).atMonth(7).atDay(24), false));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Bea", "Verdi", Year.of(1995).atMonth(8).atDay(1), false));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Andrea", "Verdi", Year.of(1995).atMonth(7).atDay(21), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lorenzo", "Valomori", Year.of(1995).atMonth(2).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Andrea", "Nanni", Year.of(1995).atMonth(3).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Bea", "Nanni", Year.of(1995).atMonth(3).atDay(11), false));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Andrea", "Fabbri", Year.of(1995).atMonth(3).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lorenzo", "Fabbri", Year.of(1995).atMonth(4).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Andrea", "Valomori", Year.of(1995).atMonth(10).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lia", "Fabbri", Year.of(1995).atMonth(12).atDay(11), false));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lorenzo", "Fabbri", Year.of(1995).atMonth(12).atDay(11), true));
			cnt.addMember(new projectFactoryimpl().getSimpleMember("Lia", "Valomori", Year.of(1995).atMonth(12).atDay(11), false));
			
			// test Container
			
			System.out.println("Inseriti "+ cnt.getMembers().size()+" membri");
			
			System.out.println("Mostro tutti i Lorenzo");
			cnt.members(e -> e.getName().equals("Lorenzo")).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
			
			System.out.println("Mostro tutti gli Andre");
			cnt.members(e -> e.getName().equals("Andrea")).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
			
			System.out.println("Mostro tutte le femmine");
			cnt.members(e -> ! e.getSex()).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
			
			System.out.println("Mostro tutte le Lia");
			cnt.findMember("Lia").forEach(e -> System.out.println(e.getName() + " " + e.getSurname() + e.getBirthday().toString()));
			
			System.out.println("Mostro i membri che oggi compiono gli anni");
		    cnt.members(e -> e.isBirthday()).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		    
		    // test checker
		    
		    Checker check = new CheckerImpl();
		    
		    System.out.println("Mostro tutte le persone che avranno il compleanno tra oggi e 15 giorni");
		    check.birthday(15, cnt.getMembers()).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		    
		    //non posso testare altro per il momento per i checker
		    
		    // Info
		    
		    InfoProject info = new InfoProjectImpl();
		    System.out.println("Mostro le informazioni riguardanti Lorenzo Valgimigli");
		    info.getMemberSpecificalInfo(cnt.members(e -> e.getName().equals("Lorenzo")
		    												&& e.getSurname().equals("Valgimigli")).get(0))
		    							.forEach(e -> System.out.println(e.getX() + ": " + e.getY()));
		    
		    
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

}
