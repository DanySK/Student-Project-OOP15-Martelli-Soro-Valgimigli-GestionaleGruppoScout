package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;

public class UnitImpl implements Unit, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Container container;
	
	public UnitImpl(final String name){
		this.name = name;
		this.container = new ContainerImpl();
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Container getContainers() {
		return this.container;
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

}
