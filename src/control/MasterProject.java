package control;

import java.io.IOException;
import java.util.List;

import control.exception.NoUnitFoundException;

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
	public void setDirectoryToSave(final String directory) throws IOException;
	/**
	 * 
	 * @return
	 */
	public String getDirectoryToSave()throws IOException;
	/**
	 * Return all possible units to load
	 * @return
	 */
	public List<String> getListOfUnit()throws IOException;
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
