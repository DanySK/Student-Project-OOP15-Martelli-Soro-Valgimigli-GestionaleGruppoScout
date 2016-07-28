package view.main_loader;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.MasterProjectImpl;
import control.UnitImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;


	/**
	 * Outer class for class LoadUnit, CreateUnit and LoaderOptions.
	 * These 3 classes are used in LoaderImpl class on app startup
	 * @author Giovanni Martelli
	 *
	 */
public class LoaderUtil extends JPanel {
	
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
	
	public class LoadUnit extends JPanel{
	
		private static final long serialVersionUID = -153428810527607474L;
		private final JPanel panelCenter;
		private final JPanel panelBottom;
		private final JButton load;
		private String selected;
		private final List<JButton> list=new ArrayList<>();
		private final JLabel label=new JLabel();
		public LoadUnit(){
			
			/*
			 * Instanzio e personalizzo i vari componenti
			 */
			
			
			panelCenter=new JPanel(new GridLayout(0, 1));
			panelBottom=new JPanel();
			load=new JButton("Carica");
			load.setEnabled(false);
			this.setLayout(new BorderLayout());
			label.setFont(new Font("Aria", Font.ITALIC, 22));
			label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			/*
			 * Apro la lista di reparti, gestendo le eccezioni
			 */
			try {
					label.setFont(new Font("Aria", Font.ITALIC, 22));
					label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
					label.setText("Scegli quale reparto caricare");
					this.add(label, BorderLayout.NORTH);
					
					/*
					 * Aggiungo i bottoni per ogni reparto, quando uno viene cliccato tutti gli altri vengono disattivati
					 * e viene attivato il tasto carica/rimuovi
					 */
					for(String i: project.getListOfUnit()){
						JButton tmp=new JButton(i);
						tmp.setFont(new Font("Aria", Font.ITALIC, 17));
						tmp.addActionListener(e->{
							load.setEnabled(true);
							selected=tmp.getText();
							for(JButton t : list){
								t.setEnabled(false);
							}
						});
						list.add(tmp);
						panelCenter.add(tmp);					
					}
					/*
					 * Quando viene cliccato il tasto Carica viene caricato il reparto e parte il programma vero e proprio
					 */
					load.addActionListener(e->{
						try {
							MyJFrameSingletonImpl.getInstance(project.loadUnit(selected));
						} catch (Exception k){
							new WarningNotice(k.getMessage());
						}
						new MainGuiImpl();
						frame.dispose();
						
					});
					
					panelBottom.add(load, BorderLayout.LINE_END);
					panelBottom.add(getBackButton(), BorderLayout.LINE_START);
					this.add(panelCenter, BorderLayout.CENTER);
					this.add(panelBottom,BorderLayout.SOUTH);
					this.validate();
				if(project.getListOfUnit().isEmpty()){
					label.setText("<html>Al momento non ci sono reparti salvati!<br>"
							+ "Torna indietro per crearne uno(utilizzando il tasto \"Crea\")</html>");
					panelCenter.setLayout(new BorderLayout());
					panelCenter.add(label, BorderLayout.CENTER);
					panelBottom.setLayout(new BorderLayout());
					panelBottom.add(getBackButton(),BorderLayout.EAST);
					this.add(panelCenter, BorderLayout.CENTER);
					this.add(panelBottom, BorderLayout.SOUTH);
					this.validate();
				}
			
			} catch (IOException e) {
				//new WarningNotice(e.getMessage());
				e.printStackTrace();
			} catch(StringIndexOutOfBoundsException e){
				e.printStackTrace();
				label.setText("<html>Al momento non ci sono reparti salvati!<br>"
						+ "Torna indietro per crearne uno(utilizzando il tasto \"Crea\"</html>");
				
				this.add(label, BorderLayout.NORTH);
				this.add(getBackButton(),BorderLayout.SOUTH);
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
		private final JLabel label;
		private final JButton save;
		private final JTextField textField;
		public CreateUnit(){
			
			/*
			 * Creo e personalizzo i vari componenti
			 */
			this.setLayout(new GridLayout(2,2));
			
			label = new JLabel("Nome Reparto: ");
			label.setFont(new Font("Aria", Font.ITALIC, 20));
			textField=new JTextField();
			save=new JButton("Save");
			save.addActionListener(e->{
				try {
					project.save(new UnitImpl(textField.getText()));
					MyJFrameSingletonImpl.getInstance(project.loadUnit(textField.getText()));
					new MainGuiImpl();
				} catch (Exception k){
					k.printStackTrace();
				}
			});
			/*
			 * Aggiungo tutti i componenti al pannello
			 */
			this.add(label);
			this.add(textField);
			this.add(save);
			this.add(getBackButton());
			
			this.frame.add(this);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setVisible(true);
			
		}
	
	}
	
	public class LoaderOptions extends JPanel{

		private static final long serialVersionUID = 93381464336093532L;
		
		private final JPanel panelOptions;
		private final JButton change;
		private final JLabel directory;
		private JFileChooser fileChooser;
		private final JPanel panelBottom;
		
		public LoaderOptions(){
			/*
			 * Instanzio e personalizzo tutti i componenti
			 */
			panelOptions=new JPanel(new GridLayout(0, 2));
			change=new JButton("Cambia");
			directory=new JLabel();
			panelBottom=new JPanel(new BorderLayout());
			this.setLayout(new BorderLayout());
			
				//Directory
			try {
				directory.setText("Directory "+project.getDirectoryToSave()+"    ");
				directory.setFont(new Font("Aria", Font.ITALIC, 10));
				fileChooser=new JFileChooser(project.getDirectoryToSave());
			} catch (IOException e) {
				new WarningNotice(e.getMessage());
			}
			
				//change
			change.addActionListener(e->{
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showSaveDialog(null);
				try{
					project.setDirectoryToSave(fileChooser.getSelectedFile().getPath());
					directory.setText("Directory"+project.getDirectoryToSave()+"    ");
				}catch(Exception k){
					new WarningNotice(k.getMessage());
				}
				this.repaint();
				this.validate();
				
			});
			panelOptions.add(directory);
			panelOptions.add(change);
			
			
			this.add(panelOptions,BorderLayout.CENTER);
			this.add(panelBottom, BorderLayout.SOUTH);
			
		}
		public JPanel getPanelBottom(){
			return this.panelBottom;
		}
		
	}
	
	private JButton getBackButton(){
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
