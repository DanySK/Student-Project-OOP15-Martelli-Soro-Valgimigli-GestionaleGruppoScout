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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.MasterProjectImpl;
import control.ProjectFactoryImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.unit_manager.utility.UnitLeaderJPanelImpl;

/**
 * Outer class for class LoadUnit, CreateUnit and LoaderOptions. These 3 classes
 * are used in LoaderImpl class on app startup
 * 
 * @author Giovanni Martelli
 *
 */
public class LoaderUtil extends MyJPanelImpl {

	private static final long serialVersionUID = 5231397320694535637L;
	private static MasterProjectImpl project;
	private final JFrame frame;

	public LoaderUtil(MasterProjectImpl pr) {
		LoaderUtil.project = pr;
		frame = new JFrame();
	}

	/**
	 * Inner Class used to load Unit(reparti) on app startup
	 * 
	 * @author Giovanni Martelli
	 *
	 */

	public class LoadUnit extends MyJPanelImpl {

		private static final long serialVersionUID = -153428810527607474L;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelBottom;
		private String selected;

		public LoadUnit() {
			super(new BorderLayout());
			/*
			 * Instanzio e personalizzo i vari componenti
			 */
			panelCenter = new MyJPanelImpl(new GridLayout(0, 1));
			panelBottom = new MyJPanelImpl();

			/*
			 * Apro la lista di reparti, gestendo le eccezioni
			 */
			try {

				this.add(createJLabel("Scegli quale reparto caricare", 22), BorderLayout.NORTH);
				/*
				 * Aggiungo i bottoni per ogni reparto, quando uno viene
				 * cliccato tutti gli altri vengono disattivati e viene attivato
				 * il tasto carica/rimuovi
				 */
				if (project.getListOfUnit().isEmpty()) {
					this.removeAll();
					this.add(
							createJLabel(
									"<html>Al momento non ci sono reparti salvati!<br>"
											+ "Torna indietro per crearne uno(utilizzando il tasto \"Crea\"</html>",
									18),
							BorderLayout.NORTH);

					this.add(getBackButtonPrivate(), BorderLayout.SOUTH);
					this.validate();
				}

				else {

					for (final String i : project.getListOfUnit()) {
						panelCenter.add(createButton(i, e -> {
							((JButton) panelBottom.getComponent(0)).setEnabled(true);
							((JButton) panelBottom.getComponent(1)).setEnabled(true);
							selected = ((JButton) e.getSource()).getName();

							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									for (final Component k : Arrays.asList(panelCenter.getComponents())) {
										k.setEnabled(false);
									}
								}
							});
						}));

					}
					Arrays.asList((panelCenter.getComponents())).stream()
							.forEach(k -> k.setFont(new Font("Aria", Font.ITALIC, 15)));
					/*
					 * Quando viene cliccato il tasto Carica viene caricato il
					 * reparto e parte il programma vero e proprio
					 */
					panelBottom.add(createButton("Carica", e -> {
						try {
							MyJFrameSingletonImpl.getInstance(project.loadUnit(selected));

							new MainGUI();

							frame.dispose();
						} catch (Exception k) {
							JOptionPane.showConfirmDialog(null, k.getMessage());
						}
					}), BorderLayout.LINE_END);
					panelBottom.add(createButton("Elimina", u -> {
						
							project.removeUnit(selected);
						

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								panelCenter.removeAll();
								try {
									for (final String i : project.getListOfUnit()) {
										panelCenter.add(createButton(i, e -> {
											((JButton) panelBottom.getComponent(0)).setEnabled(true);
											selected = ((JButton) e.getSource()).getName();

											SwingUtilities.invokeLater(new Runnable() {
												@Override
												public void run() {
													for (final Component k : Arrays.asList(panelCenter.getComponents())) {
														k.setEnabled(false);
													}
												}
											});
										}));

									}
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
								panelCenter.validate();
								panelCenter.repaint();
							}
						});

					}));
					panelBottom.getComponent(0).setEnabled(false);
					panelBottom.getComponent(1).setEnabled(false);
					panelBottom.add(getBackButtonPrivate(), BorderLayout.LINE_START);
					this.add(panelCenter, BorderLayout.CENTER);
					this.add(panelBottom, BorderLayout.SOUTH);
					this.validate();
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(null, e.getMessage());
			}

			frame.add(this);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	public class CreateUnit extends JPanel {

		private static final long serialVersionUID = 6411454791261548663L;
		private final JTextField textField;

		public CreateUnit() {

			/*
			 * Creo e personalizzo i vari componenti
			 */
			super(new BorderLayout());
			final MyJPanelImpl capi = new MyJPanelImpl(new GridLayout(0, 1));
			final MyJPanelImpl buttons = new MyJPanelImpl();
			textField = new JTextField();
			final MyJPanelImpl nome = new MyJPanelImpl(new GridLayout(0, 2));
			nome.add(createJLabel("Nome Reparto: ", 20));
			nome.add(textField);
			final UnitLeaderJPanelImpl capoM = new UnitLeaderJPanelImpl("Capo Maschio");
			final UnitLeaderJPanelImpl capoF = new UnitLeaderJPanelImpl("Capo Femmina");
			capi.add(capoM);
			capi.add(capoF);
			this.add(nome, BorderLayout.NORTH);
			this.add(capi, BorderLayout.CENTER);

			buttons.add(createButton("Crea", e -> {
				
					
					String str;
					if(textField.getText().isEmpty()){
						str="non settato";
					}
					else{
						str=textField.getText();
					}
					
					try{
						project.save(ProjectFactoryImpl.getUnit(ProjectFactoryImpl.getReparto(
					
							ProjectFactoryImpl.getLeaderM(capoM.getNome(), capoM.getSurname(), capoM.getDate(),
									capoM.getPhone()),
							ProjectFactoryImpl.getLeaderF(capoF.getNome(), capoF.getSurname(), capoF.getDate(),
									capoF.getPhone()),
							str)));
						MyJFrameSingletonImpl.getInstance(project.loadUnit(str.replaceAll(" ", "_")));
						
						frame.dispose();
						new MainGUI();
					}catch(Exception k){
						JOptionPane.showMessageDialog(null, k.getMessage());
					}
					System.out.println("CACCA");
					

					
					

			
			}));
			buttons.add(getBackButtonPrivate());
			this.add(buttons, BorderLayout.SOUTH);
			/*
			 * Aggiungo il tutto al frame
			 */
			frame.add(this);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		}

	}

	public class LoaderOptions extends JPanel {

		private static final long serialVersionUID = 93381464336093532L;

		private final MyJPanelImpl panelOptions;
		private JFileChooser fileChooser;
		private final MyJPanelImpl panelBottom;

		public LoaderOptions() {
			super();
			/*
			 * Instanzio e personalizzo tutti i componenti
			 */
			panelOptions = new MyJPanelImpl(new GridLayout(0, 2));
			panelBottom = new MyJPanelImpl();
			this.setLayout(new BorderLayout());

			// Directory
			try {
				panelOptions.add(createJLabel("Directory: " + project.getDirectoryToSave() + "    ", 12));
				fileChooser = new JFileChooser(project.getDirectoryToSave());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			panelOptions.add(createButton("Cambia", e -> {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showSaveDialog(null);

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						try {
							project.setDirectoryToSave(fileChooser.getSelectedFile().getPath());
							((JLabel) panelOptions.getComponent(0))
									.setText("Directory: " + project.getDirectoryToSave() + "    ");
						} catch (Exception k) {
							JOptionPane.showMessageDialog(null, k.getMessage());
						}
					}
				});
				this.repaint();
				this.validate();

			}));

			this.add(panelOptions, BorderLayout.CENTER);
			this.add(panelBottom, BorderLayout.SOUTH);

		}

		public JPanel getPanelBottom() {
			return this.panelBottom;
		}

	}

	private JButton getBackButtonPrivate() {
		final ImageIcon img = new ImageIcon("res/back-icon-small.png");
		final JButton t = new JButton("Back");
		t.setIcon(img);
		t.addActionListener(e -> {
			new LoaderImpl();
			frame.dispose();
		});
		return t;
	}
}
