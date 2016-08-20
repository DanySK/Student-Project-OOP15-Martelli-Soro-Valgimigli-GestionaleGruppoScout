package control;

import java.io.IOException;
import java.util.List;

import control.exception.ProjectFilesCreationException;

/**
 * Class with tools to handle the project as saving, loading, check file Default
 * directory to save /home/user/ScoutApp/Save
 * 
 * @author Valgio
 *
 */
public interface MasterProject {
	/**
	 * To modify the default directory
	 * 
	 * @param directory
	 */
	void setDirectoryToSave(final String directory) throws IOException;

	/**
	 * 
	 * @return
	 * the directory where the program save
	 */
	String getDirectoryToSave() throws IOException;

	/**
	 * Return all possible units to load
	 * 
	 * @return
	 */
	List<String> getListOfUnit() throws IOException;

	/**
	 * Method that takes a unit from directory to save and load the information ina Unit
	 * @param unitName
	 * @return A unit selected
	 * @throws NoUnitFoundException
	 */
	Unit loadUnit(String unitName) throws IOException, ClassNotFoundException;

	/**
	 * Method that writes the unit information in a .sct file 
	 * @param unit
	 */
	void save(Unit unit) throws IOException, ProjectFilesCreationException;
	/**
	 * Method that removes saving file of a Specific unit
	 * @param unitName
	 * @throws IllegalArgumentException
	 */
	void removeUnit(String unitName) throws IllegalArgumentException;
}
