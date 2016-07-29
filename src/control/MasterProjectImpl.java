package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import control.exception.DefaultDirectoryException;
import control.exception.ProjectFilesCreationException;
import model.RepartoImpl;
import view.general_utility.WarningNotice;

public class MasterProjectImpl implements MasterProject {
	
	private final static String DEFAULT_DIRECTORY = System.getProperty("user.home")
													+System.getProperty("file.separator")
													+"ScoutApp";
	private final static String DEFAULT_DIR_TOSAVE = DEFAULT_DIRECTORY + System.getProperty("file.separator")
													+ "SaveProject";
	private final static String impFile = DEFAULT_DIRECTORY + System.getProperty("file.separator") + "ImpScout.txt";
	private final static String PROJECT_EXTENSION = ".sct";
	
	private File worker = null;
	private String directoryToSave = null;
	private BufferedReader reader;
	private BufferedWriter writer;
	private ObjectInputStream loader;
	private ObjectOutputStream saver;
	
	
	public MasterProjectImpl() throws Exception{
		worker = new File(DEFAULT_DIRECTORY);
		if(! this.worker.exists()){
			if(! this.worker.mkdir()){
				throw new DefaultDirectoryException();
			}
			this.worker = new File(impFile);
			if(!this.worker.createNewFile()){
				throw new ProjectFilesCreationException();
			}
			this.setDirectoryToSave(DEFAULT_DIR_TOSAVE);
			this.worker = new File(DEFAULT_DIR_TOSAVE);
			if(!this.worker.mkdir()){
				throw new ProjectFilesCreationException();
			}
		}
	}

	@Override
	public void setDirectoryToSave(String directory) throws IOException {
		this.writer = new BufferedWriter( new FileWriter(impFile));
		this.writer.write(directory);
		this.writer.newLine();
		this.writer.close();
	}

	@Override
	public String getDirectoryToSave() throws IOException {
		this.reader = new BufferedReader(new FileReader(impFile));
		String dir = this.reader.readLine();
		this.reader.close();
		return dir;
	}

	@Override
	public List<String> getListOfUnit() throws IOException{
		this.directoryToSave = this.getDirectoryToSave();
		this.worker = new File(this.directoryToSave);
		String[] files = this.worker.list();
		List<String> filesList = Arrays.asList(files);
		return filesList.stream().filter(e -> e.endsWith(PROJECT_EXTENSION))
								 .map(e -> e.substring(0, e.length() - 4)).collect(Collectors.toList());
	}

	@Override
	public Unit loadUnit(String unitName) throws IOException, ClassNotFoundException {
		this.directoryToSave = this.getDirectoryToSave();
		String files = this.directoryToSave + System.getProperty("file.separator") + unitName + PROJECT_EXTENSION;
		this.loader = new ObjectInputStream(new FileInputStream(files));
		Unit unit = (Unit) this.loader.readObject();
		this.loader.close();
		return unit;
	}

	@Override
	public void save(Unit unit) throws IOException, ProjectFilesCreationException {
		this.directoryToSave = this.getDirectoryToSave();
		String files = this.directoryToSave + System.getProperty("file.separator") + unit.getName() + PROJECT_EXTENSION;
		System.out.println(files);
		this.saver = new ObjectOutputStream(new FileOutputStream(files));
		this.worker = new File(files);
		if(! this.worker.exists()){
			if(! this.worker.createNewFile()){
				throw new ProjectFilesCreationException();
			}
		}
		this.saver.writeObject(unit);
		this.saver.close();
		new WarningNotice("Salvataggio avvenuto con successo");
	}
	
	public static void main (String[] s){
		try {
			Unit u1 = new UnitImpl(new RepartoImpl(projectFactoryimpl.getLeaderM("Mario", "Verdi") ,
					projectFactoryimpl.getLeaderF("Anna", "Rossi"), new ArrayList<>(), "Il falco rosa"));
			Unit u2 = new UnitImpl(new RepartoImpl(projectFactoryimpl.getLeaderM("gio", "Verdi") ,
					projectFactoryimpl.getLeaderF("bea", "Rossi"), new ArrayList<>(), "la mela verde"));
			Unit u3 = new UnitImpl(new RepartoImpl(projectFactoryimpl.getLeaderM("sandro", "Verdi") ,
					projectFactoryimpl.getLeaderF("lia", "Rossi"), new ArrayList<>(), "Il falco viola"));
			Unit u4 = new UnitImpl(new RepartoImpl(projectFactoryimpl.getLeaderM("gim", "Verdi") ,
					projectFactoryimpl.getLeaderF("gio", "Rossi"), new ArrayList<>(), "Il culo rosa"));
			
			// test saving
			
			MasterProject mp = new MasterProjectImpl();
			mp.save(u1);
			mp.save(u2);
			mp.save(u3);
			mp.save(u4);
			File file = new File(mp.getDirectoryToSave() + "blabla");
			
			// creo dei file a caso nella directory
			
			file.createNewFile();
			mp.getListOfUnit().forEach(System.out :: println);
			
			// carico una classe salvata
			
			Unit u2tmp = mp.loadUnit(u2.getName());
			if(u2tmp.equals(u2)){
				System.out.println("Caricamento Corretto");
			}else{
				System.out.println(u2tmp.info());
			}
			
			// carico una classe trmite la lista
			Unit u3tmp = mp.loadUnit(mp.getListOfUnit().get(mp.getListOfUnit().indexOf(u3.getName())));
			if(u3tmp.equals(u3)){
				System.out.println("Caricamento 2 Corretto");
			}else{
				System.out.println(u3tmp.info());
			}
			
			u2tmp.setName("Blablabla");
			
			mp.save(u2tmp);
			
			Unit u2tmp2 = mp.loadUnit(u2tmp.getName());
			System.out.println(u2tmp2.info());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
