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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import control.exception.DefaultDirectoryException;
import control.exception.ProjectFilesCreationException;
import model.escursioni.Uscita;
import model.reparto.Capo;
import model.reparto.Reparto;
import model.reparto.Roles;
import model.reparto.Squadron;

public class MasterProjectImpl implements MasterProject {

	private final static String FILESEPARATOR = "file.separator";
	private final static String DEFAULT_DIRECTORY = System.getProperty("user.home") + System.getProperty(FILESEPARATOR)
			+ "ScoutApp";
	private final static String DEFAULT_DIR_TOSAVE = DEFAULT_DIRECTORY + System.getProperty(FILESEPARATOR)
			+ "SaveProject";
	private final static String IMPFILE = DEFAULT_DIRECTORY + System.getProperty(FILESEPARATOR) + "ImpScout.txt";
	private final static String PROJECT_EXTENSION = ".sct";
	private final static String DEMO_PROJECT = "demoVersion.sct";

	private String directoryToSave;

	public MasterProjectImpl() throws DefaultDirectoryException, IOException, ProjectFilesCreationException {
		File worker = new File(DEFAULT_DIRECTORY);
		if (!worker.exists()) {
			if (!worker.mkdir()) {
				throw new DefaultDirectoryException();
			}
			worker = new File(IMPFILE);
			if (!worker.createNewFile()) {
				throw new ProjectFilesCreationException();
			}
			this.directoryToSave = DEFAULT_DIR_TOSAVE;
			final BufferedWriter writer = new BufferedWriter(new FileWriter(IMPFILE));
			writer.write(this.directoryToSave);
			writer.newLine();
			writer.close();
			worker = new File(DEFAULT_DIR_TOSAVE);
			if (!worker.mkdir()) {
				throw new ProjectFilesCreationException();
			}
		}
		System.out.println("Creating Demo");
		this.createDemoVersion();
	}

	@Override
	public void setDirectoryToSave(final String directory) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(IMPFILE));
		writer.write(directory);
		writer.newLine();
		writer.close();
	}

	@Override
	public String getDirectoryToSave() throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(IMPFILE));
		final String dir = reader.readLine();
		reader.close();
		return dir;
	}

	@Override
	public List<String> getListOfUnit() throws IOException {
		this.directoryToSave = this.getDirectoryToSave();
		final File worker = new File(this.directoryToSave);
		final String[] files = worker.list();
		final List<String> filesList = Arrays.asList(files);
		return filesList.stream().filter(e -> e.endsWith(PROJECT_EXTENSION)).map(e -> e.substring(0, e.length() - 4))
				.collect(Collectors.toList());
	}

	@Override
	public Unit loadUnit(final String unitName) throws IOException, ClassNotFoundException {
		this.directoryToSave = this.getDirectoryToSave();
		final String files = this.directoryToSave + System.getProperty(FILESEPARATOR) + unitName + PROJECT_EXTENSION;
		final ObjectInputStream loader = new ObjectInputStream(new FileInputStream(files));
		final Unit unit = (Unit) loader.readObject();
		loader.close();
		return unit;
	}

	@Override
	public void save(final Unit unit) throws IOException, ProjectFilesCreationException {
		this.directoryToSave = this.getDirectoryToSave();
		final String files = this.directoryToSave + System.getProperty(FILESEPARATOR) + unit.getName()
				+ PROJECT_EXTENSION;
		final File worker = new File(files);
		if (!worker.exists() && !worker.createNewFile()) {
			throw new ProjectFilesCreationException();
		}
		final ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream(files));
		saver.writeObject(unit);
		saver.close();
		JOptionPane.showMessageDialog(null, "Salvataggio avvenuto con successo");
	}
	@Override
	public void removeUnit(final String unitName) throws IllegalArgumentException{
		final File worker = new File(this.directoryToSave + System.getProperty(FILESEPARATOR)
			+ unitName + PROJECT_EXTENSION);
		if(worker.exists()){
			if(! worker.delete()){
				throw new IllegalArgumentException();
			}
		}else{
			throw new IllegalArgumentException();
		}
	}
	/*
	 * Method creates to get a demo version for testing
	 */
	private void createDemoVersion(){
		String file;
		try {
			file = this.getDirectoryToSave();
			
			final File worker = new File(file);
			final List<String> files = Arrays.asList(worker.list());
			System.out.println(files);
		
			if(! files.contains(DEMO_PROJECT)){
				Capo leaderM = ProjectFactoryImpl.getLeaderM("Marco", "Mitraglia", LocalDate.of(1993, 11, 9), "3454565678");
				Capo leaderF = ProjectFactoryImpl.getLeaderF("Marcella", "Rossi", LocalDate.of(1993, 11, 9), "3985657890");
				Reparto rp = ProjectFactoryImpl.getReparto(leaderM, leaderF, "demoVersion");
				Unit demo = ProjectFactoryImpl.getUnit(rp);
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Andrea", "Rossi", LocalDate.of(2002, 8, 24), true));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Lollo", "Verdi", LocalDate.of(2002, 8, 31), true));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Riki", "Blu", LocalDate.of(2002, 9, 6), true));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Mario", "Rasi", LocalDate.of(2003, 9, 13), true));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Anna", "Proti", LocalDate.of(2002, 9, 19), false));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Gio", "Prati", LocalDate.of(2002, 9, 25), false));
				demo.addMember(ProjectFactoryImpl.getSimpleMember("Selly", "Sani", LocalDate.of(2003, 10, 1), false));
				demo.createSq(ProjectFactoryImpl.getSquadron("Aquile", true));
				Squadron tmp = demo.getContainers().findSquadron("Aquile");
				demo.putMemberInSq(demo.getContainers().getMember("Andrea", "Rossi"), tmp , Roles.CUCINIERE);
				demo.putMemberInSq(demo.getContainers().getMember("Lollo", "Verdi"), tmp , Roles.MAGAZZINIERE);
				demo.putMemberInSq(demo.getContainers().getMember("Riki", "Blu"), tmp , Roles.GUARDIANO_ANGOLO);
				Uscita uscita = ProjectFactoryImpl.getStdExcursion(LocalDate.now().plus(5, ChronoUnit.DAYS),
						demo.getReparto(), "Uscita dei Passaggi");
				demo.addExcursion(uscita);
				
				this.save(demo);
			}
			}catch(Exception e){ e.printStackTrace();};
		
	}

}
