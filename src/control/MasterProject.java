package control;

import java.util.List;

/**
 * Class with tools to handle the project as saving, loading, check file 
 * Default directory to save /home/user/ScoutApp/Save 
 * @author Valgio
 *
 */
public interface MasterProject {
	/**
	 * To modify the default directory 
	 * @param directory
	 */
	public void setDirectoryToSave(final String directory);
	/**
	 * 
	 * @return
	 */
	public String getDirectoryToSave();
	/**
	 * Return all possible units to load
	 * @return
	 */
	public List<String> getListOfUnit();
	/**
	 * 
	 * @param unitName
	 * @return
	 * @throws NoUnitFoundException
	 */
	public Unit loadUnit(String unitName) throws NoUnitFoundException;
	/**
	 * 
	 * @param unit
	 */
	public void save(Unit unit);
}
