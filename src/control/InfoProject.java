package control;

import java.util.List;

import control.myUtil.Pair;
import model.Member;

/**
 * Class with all tool to get informations about squadron, member, unit
 * @author Valgio
 *
 */
public interface InfoProject {

	
	
	/**
	 * Method that provide all info about squadron: Name, sex, Name of Leader, Name of deputy, Name of 3rd,
	 * number of member 
	 * @return
	 */
	public String getSquadronGeneralInfo(String nameOfSquadron, Container cnt);
	/**
	 *  Special method that provide a List of pair with the general info
	 * @return
	 *  A list of array. In each cell there is a specifically information
	 * 0. Name
	 * 1. sex
	 * 2. Name of Leader
	 * 3. Name of deputy
	 * 4. Name of 3rd
	 * 5. Number of member
	 */
	public List<Pair<String,String>> getSquadronSpecificInfo(String nameOfSquadron, Container cnt);
	/**
	 * 
	 * @param nameOfSquadron
	 * @return
	 * A list with all member in a squadron 
	 */
	public List<Member> getMemberOfSquadron(String nameOfSquadron, Container cnt);
	/**
	 * Method that provide all info about a mameber in input
	 * @param member
	 * @return
	 * A list of array. In each cell there is a specifically information
	 * 0. Name
	 * 1. Surname
	 * 2. sex
	 * 3. age
	 * 4. promise
	 * 5. totem
	 * 6. Birthday
	 */
	public List<Pair<String, String>> getMemberSpecificalInfo(Member member, Container cnt);
}
