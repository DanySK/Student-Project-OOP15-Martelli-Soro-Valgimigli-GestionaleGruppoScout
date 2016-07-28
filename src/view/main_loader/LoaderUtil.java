package view.main_loader;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.MasterProjectImpl;
import control.UnitImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;


	/**
	 * Outer class for class LoadUnit, CreateUnit and LoaderOptions.
	 * These 3 classes are used in LoaderImpl class on app startup
	 * @author Giovanni Martelli
	 *
	 */
public class LoaderUtil extends MyJPanelImpl {
	
	private static final long serialVersionUID = 5231397320694535637L;
	private static MasterProjectImpl project;
	private final JFrame frame;
	
	public LoaderUtil(MasterProjectImpl pr){
		LoaderUtil.project=pr;
		frame=new JFrame();
	}
	
	/**
	 * Inner Class used to load Unit(reparti) on app startup
	 * @author Giovanni Martelli
	 *
	 */
	
	public class LoadUnit extends MyJPanelImpl{
	
		private static final long serialVersionUID = -153428810527607474L;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelBottom;
		private String selected;
		public LoadUnit(){
			super(new BorderLayout());
			/*
			 * Instanzio e personalizzo i vari componenti
			 */
			panelCenter=new MyJPanelImpl(new GridLayout(0, 1));
			panelBottom=new MyJPanelImpl();
			
			/*
			 * Apro la lista di reparti, gestendo le eccezioni
			 */
			try {
				
					this.add(createJLabel("label", "Scegli quale reparto caricare", 22),BorderLayout.NORTH);
					/*
					 * Aggiungo i bottoni per ogni reparto, quando uno viene cliccato tutti gli altri vengono disattivati
					 * e viene attivato il tasto carica/rimuovi
					 */
					for(String i: project.getListOfUnit()){
						panelCenter.add(createButton(i, e->{
							((JButton)panelBottom.getComponent("Carica")).setEnabled(true);
							selected=((JButton)panelCenter.getComponent(i)).getText();
							SwingUtilities.invokeLater(new Runnable(){
									@Override
									public void run() {
										for(Component k : Arrays.asList(panelCenter.getComponents())){
											k.setEnabled(false);
										}
									}
							});
						}));
						((JButton)panelCenter.getComponent(i)).setFont(new Font("Aria", Font.ITALIC, 15));
					}
										
					/*
					 * Quando viene cliccato il tasto Carica viene caricato il reparto e parte il programma vero e proprio
					 */
					panelBottom.add(createButton("Carica", e->{
						try{
							MyJFrameSingletonImpl.getInstance(project.loadUnit(selected));
							new MainGuiImpl();
						} catch (Exception k){
							new WarningNotice(k.getMessage());
						}
					}),BorderLayout.LINE_END);
					panelBottom.add(getBackButtonPrivate(), BorderLayout.LINE_START);
					this.add(panelCenter, BorderLayout.CENTER);
					this.add(panelBottom,BorderLayout.SOUTH);
					this.validate();
								
			} catch (IOException e) {
				e.printStackTrace();
			} catch(StringIndexOutOfBoundsException e){
				this.removeAll();
				this.add(createJLabel("label","<html>Al momento non ci sono reparti salvati!<br>"
						+ "Torna indietro per crearne uno(utilizzando il tasto \"Crea\"</html>", 18),BorderLayout.NORTH);
	
				this.add(getBackButtonPrivate(),BorderLayout.SOUTH);
				this.validate();
			}
			
			frame.add(this);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}	
	}

	public class CreateUnit extends JPanel{
  		
		private static final long serialVersionUID = 6411454791261548663L;
		private final JFrame frame=new JFrame();
		private final JTextField textField;
		public CreateUnit(){
			
			/*
			 * Creo e personalizzo i vari componenti
			 */
			super(new GridLayout(2,2));
			textField=new JTextField();
		
			this.add(createJLabel("label", "Nome Reparto: ", 20));
			this.add(textField);
			this.add(createButton("Crea", e->{
				try {
					project.save(new UnitImpl(textField.getText()));
					MyJFrameSingletonImpl.getInstance(project.loadUnit(textField.getText()));
					new MainGuiImpl();
				} catch (Exception k){
					k.printStackTrace();
				}
			}));
			this.add(getBackButtonPrivate());
			/*
			 * Aggiungo il tutto al frame
			 */
			this.frame.add(this);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setVisible(true);
			
		}
	
	}
	
	public class LoaderOptions extends JPanel{

		private static final long serialVersionUID = 93381464336093532L;
		
		private final MyJPanelImpl panelOptions;
		private JFileChooser fileChooser;
		private final MyJPanelImpl panelBottom;
		
		public LoaderOptions(){
			/*
			 * Instanzio e personalizzo tutti i componenti
			 */
			panelOptions=new MyJPanelImpl(new GridLayout(0, 2));
			panelBottom=new MyJPanelImpl(new BorderLayout());
			this.setLayout(new BorderLayout());
			
				//Directory
			try {
				panelOptions.add(createJLabel("directory", "Directory: "+project.getDirectoryToSave()+"    ", 12));
				panelOptions.getComponent("directory").setFont(new Font("Aria", Font.BOLD,12));
				fileChooser=new JFileChooser(project.getDirectoryToSave());
			} catch (IOException e) {
				new WarningNotice(e.getMessage());
			}
			panelOptions.add(createButton("Cambia", e->{
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showSaveDialog(null);
				try{
					project.setDirectoryToSave(fileChooser.getSelectedFile().getPath());
					((JLabel)panelOptions.getComponent("directory")).setText("Directory: "+project.getDirectoryToSave()+"    ");
				}catch(Exception k){
					new WarningNotice(k.getMessage());
				}
				this.repaint();
				this.validate();
				
			}));
				
			this.add(panelOptions,BorderLayout.CENTER);
			this.add(panelBottom, BorderLayout.SOUTH);
			
		}
		public JPanel getPanelBottom(){
			return this.panelBottom;
		}
		
	}
	
	private JButton getBackButtonPrivate(){
		ImageIcon img = new ImageIcon("res/back-icon-small.png");
		JButton t=new JButton("Back");
		t.setIcon(img);
		t.addActionListener(e->{
			new LoaderImpl();
			frame.dispose();
		});
		return t;
	}
}
