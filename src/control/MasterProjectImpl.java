package control;

import java.io.File;
import java.util.List;

import control.exception.DefaultDirectoryException;
import control.exception.NoUnitFoundException;

public class MasterProjectImpl implements MasterProject {
	
	private final static String DEFAULT_DIRECTORY = System.getProperty("user.home")
													+System.getProperty("file.separator")
													+"ScoutApp";
	private final static String impFile = DEFAULT_DIRECTORY + System.getProperty("file.separator") + "ImpScout.txt";
	private File worker = null;
	
	
	public MasterProjectImpl() throws DefaultDirectoryException{
		worker = new File(DEFAULT_DIRECTORY);
		if(! this.worker.exists()){
			if(! this.worker.mkdir()){
				throw new DefaultDirectoryException();
			}
			this.worker = new File(impFile);
			
		}
	}

	@Override
	public void setDirectoryToSave(String directory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDirectoryToSave() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getListOfUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit loadUnit(String unitName) throws NoUnitFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Unit unit) {
		// TODO Auto-generated method stub
		
	}

}
