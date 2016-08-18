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
	 */
	String getDirectoryToSave() throws IOException;

	/**
	 * Return all possible units to load
	 * 
	 * @return
	 */
	List<String> getListOfUnit() throws IOException;

	/**
	 * 
	 * @param unitName
	 * @return
	 * @throws NoUnitFoundException
	 */
	Unit loadUnit(String unitName) throws IOException, ClassNotFoundException;

	/**
	 * 
	 * @param unit
	 */
	void save(Unit unit) throws IOException, ProjectFilesCreationException;
}
