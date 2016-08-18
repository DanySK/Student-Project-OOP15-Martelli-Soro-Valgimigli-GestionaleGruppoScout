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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import control.exception.DefaultDirectoryException;
import control.exception.ProjectFilesCreationException;
import view.general_utility.WarningNotice;

public class MasterProjectImpl implements MasterProject {

	private final static String FILESEPARATOR = "file.separator";
	private final static String DEFAULT_DIRECTORY = System.getProperty("user.home") + System.getProperty(FILESEPARATOR)
			+ "ScoutApp";
	private final static String DEFAULT_DIR_TOSAVE = DEFAULT_DIRECTORY + System.getProperty(FILESEPARATOR)
			+ "SaveProject";
	private final static String IMPFILE = DEFAULT_DIRECTORY + System.getProperty(FILESEPARATOR) + "ImpScout.txt";
	private final static String PROJECT_EXTENSION = ".sct";

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
			worker = new File(DEFAULT_DIR_TOSAVE);
			if (!worker.mkdir()) {
				throw new ProjectFilesCreationException();
			}
		}
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
		new WarningNotice("Salvataggio avvenuto con successo");
	}

}
