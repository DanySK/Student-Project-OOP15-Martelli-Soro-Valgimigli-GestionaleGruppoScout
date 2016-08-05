package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.InfoProjectImpl;
import control.Unit;
import control.myUtil.Pair;
import model.Member;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanel;
import view.gui_utility.MyJPanelImpl;

public class EditableInfoPanelImpl extends MyJPanelImpl {
	
	private static final long serialVersionUID = 3525477272751960285L;

	/*
	 * Enum contenente le voci per le info riguardanti l'utente
	 */
	enum ChefLabel{
		Nome("Nome: ", ""),
		Sesso("Sesso: ", ""),
		Capo("Capo: ", "" ),
		Vice("Vice: ", ""),
		Trice("Trice: ", ""),
		Membri("Numero di membri: ", "");
		private final String name;
		private String value;
		
		public String getName(){return this.name;}
		public String getValue(){return this.value;}
		public void setValue(String value){this.value=value;}
		private ChefLabel(String name, String value){
			this.name=name;
			this.value=value;
		}
	}
	
	private final String squadName;
	private final boolean editable;
	private final int fontSizeButton;
	private final int fontSizeLabel;
	private List<Pair<String,String>> list=new ArrayList<>();
	private MyJPanelImpl panelSx;
	private MyJPanelImpl panelDx;
	private MyJPanelImpl panelBot;
	private final Unit unit= MyJFrameSingletonImpl.getInstance().getUnit();
	public EditableInfoPanelImpl(String squad,boolean isManager, int fontSizeLabel, int fontSizeButton){
		super(new BorderLayout());
		this.editable=isManager;
		this.fontSizeLabel=fontSizeLabel;
		this.fontSizeButton=fontSizeButton;
		panelSx = new MyJPanelImpl(new GridLayout(0,2));
		panelDx=new MyJPanelImpl(new GridLayout(0, 1));
		panelBot=new MyJPanelImpl(new GridLayout(0, 3));
		this.squadName=squad;
		
		
		/*
		 * Aggiungo il panello con tutte le info
		 */
		this.updateInfo();
		/*
		 * Aggiung i bottoni Cancelleria, Batteria e Cassa
		 * 
		 */
		List<Pair<String,String>> list = new ArrayList<>();
		list.add(new Pair<>("Cancelleria", unit.getContainers().findSquadron(squadName).getNoteCancelleria()));
		list.add(new Pair<>("Batteria", unit.getContainers().findSquadron(squadName).getNoteBatteria()));
		list.add(new Pair<>("Cassa", unit.getContainers().findSquadron(squadName).getNoteCassa()));
		list.stream().forEach(e->{
			panelBot.add(createButton("Note "+e.getX().toLowerCase(), k->{
				JDialog dial = new JDialog();
				JPanel panel = new JPanel(new BorderLayout());
				JPanel bot=new JPanel();
				JTextArea area=createJTextArea("areaText", e.getY(), isManager, fontSizeLabel);
				if(!editable && e.getY().isEmpty()){
					area.setText("Non ci sono note di "+e.getX().toLowerCase()+" presenti."+System.lineSeparator()+
							"Recarsi nella sezione di gestione della squadriglia per aggiungerle");
				}
				
				panel.add(area);
				if(editable){
					bot.add(createButton("Ok", t->{
						try {/*Utilizzo la reflection*/
							Method m=unit.getContainers().findSquadron(squadName).getClass().getDeclaredMethod("setNote"+e.getX(), String.class );
							m.invoke(unit.getContainers().findSquadron(squadName),area.getText());/*chiamata al metodo trovato*/
							MyJFrameSingletonImpl.getInstance().setNeedToSave();
							dial.dispose();
						} catch (Exception g){
							new WarningNotice(g.getMessage());
						}
					}));
					bot.add(createButton("Annulla", t->{
						dial.dispose();
					}));
				}
				else{
					bot.add(createButton("Ok", g->{
						dial.dispose();
					}));
				}
				panel.add(createJLabel("labelNorth", "<html><U>"+(!editable?"Note ":"Modifica note ")+
						e.getX().toLowerCase()+"</U></html>", fontSizeLabel), BorderLayout.NORTH);
				panel.add(bot, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(null);
				dial.setVisible(true);
			}));
		});
		for(Component i: panelBot.getComponents()){
			i.setFont( new Font("Aria", Font.ITALIC,fontSizeButton+3));
		}
		
	}
	
	private boolean isUsefull(String param){
		/*controllo se è "utile" il campo richiesto*/
		if(!editable){//false per Overview-->solo Nome
			if(param.equals("Nome: ")){return false;}
			return true;
		}
		else{//false per Manager-->Nome,Numero di membri e sesso
			if(param.equals("Nome: ") || param.equals("Numero di membri: ") || param.equals("Sesso: ")){return false;}
		}
		return true;
	}
	
	private JButton instanceJButton(Pair<String,String> i){
		/*
		 * nel caso di pannello per mostrare solo informazioni
		 */
		if(!editable){
			JButton ret= createButton("info",this.getBackground(), new Font("Aria", Font.ITALIC,fontSizeButton),e->{
				JDialog dial = new JDialog();
				JPanel panel=new JPanel(new BorderLayout());
				JPanel bot= new JPanel();
				JTextArea area= createJTextArea("area", "", false, 18);
				String k = i.getY();
				List<Pair<String,String>> areaText= (new InfoProjectImpl()).getMemberSpecificalInfo(MyJFrameSingletonImpl.getInstance()
					.getUnit().getContainers().getMember(k.substring(0, k.indexOf(" ")), k.substring(k.indexOf(" ")+1)));
														/*Nome*/						/*Cognome*/
				areaText.forEach(t->{
					area.append(t.getX()+": "+t.getY()+System.lineSeparator());//per ogni info aggiungo una riga alla areaText
				});
				panel.add(area,BorderLayout.CENTER);
				bot.add(createButton("Ok", g->{
					dial.dispose();
				}));
				panel.add(bot, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(null);
				dial.setVisible(true);
			});
			if(i.getY().equals("non settato")){ret.setEnabled(false);}
			return ret;
		}
		/*
		 * nel caso di pannello per modificare informazioni
		 */
		else return createButton("edit", UIManager.getColor(new JButton()), new Font("Aria", Font.ITALIC,fontSizeButton), e->{
			if(!i.getY().equals("non settato")){
				/*
				 * Se diverso da "non settato" avviso l'utente che potrebbe perdere dei dati
				 */
				new WarningNotice("<html><U>ATTENZIONE!!</U><br>"
						+ "La carica è già stata assegnata.<br>Continuando e salvando verrà riassegnata.</html>");
			}
			JDialog dial=new JDialog();
			JPanel pane=new JPanel(new BorderLayout());
			JPanel bot=new JPanel();
			pane.add(createJLabel("northLabel", "<html><U>Assegna carica</U></html>", fontSizeLabel),BorderLayout.NORTH);
			JPanel Center=new JPanel(new GridLayout(2,2));
			Center.add(createJLabel("nome", "Nome:", fontSizeLabel));
			JTextField nome=new JTextField();
			Center.add(nome);
			Center.add(createJLabel("congnome", "Cognome:", fontSizeLabel));
			JTextField cognome=new JTextField();
			Center.add(cognome);
			bot.add(createButton("Salva",k->{
				//se non trovo nessun member salvato con quel nome e cognome avviso utente
				if(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri().keySet()
						.stream().filter(t->t.getName().equals(nome.getText())&&t.getSurname().equals(cognome.getText()))
						.collect(Collectors.toList()).isEmpty()){
					new WarningNotice("<html>Nessun membro trovato con quel nome.<br>"
							+ "Controllare i dati inseriti e riprovare</html>");
				}
				else{
					//se ci sono più membri con quel nome faccio scegliere all'utente quale utilizzare
					if(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri().keySet()
						.stream().filter(t->t.getName().equals(nome.getText())&&t.getSurname().equals(cognome.getText()))
						.collect(Collectors.toList()).size() > 1){
					/*ImplementareChooser*/
					}
					String method="set"+i.getX().substring(0, i.getX().length()-2)+((i.getX().equals("Vice: "))?"capoSq":"Sq");
					try {//Utilizzo reflection
						Method mt=MyJFrameSingletonImpl.getInstance().getUnit()//ricavo metodo
								.getContainers().findSquadron(squadName).getClass().getDeclaredMethod(method, Member.class);
							//lancio metodo
						mt.invoke(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName),
							MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri().keySet()
							.stream().filter(t->t.getName().equals(nome.getText())&&t.getSurname().equals(cognome.getText())).findFirst().get());
						MyJFrameSingletonImpl.getInstance().setNeedToSave();//setto che sono avvenuti dei cambiamenti
						new WarningNotice("<html>Membro trovato e settato come "+i.getX().toLowerCase()+"<br>"
								+"Ricordati di salvare o perderai le modifiche </html>");
						updateInfo();
						dial.dispose();
					} catch (Exception e1) {
						new WarningNotice(e1.getMessage());
					} 				
				}
				
			}));
			bot.add(createButton("Annulla", t->dial.dispose()));
			pane.add(Center, BorderLayout.CENTER);
			pane.add(bot,BorderLayout.SOUTH);
			dial.add(pane);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		});
		
	}
	public MyJPanel getPanelSx(){
		return this.panelSx;
	}
	
	public MyJPanel getPanelDx(){
		return this.panelDx;
		
	}
	
	public void addButtonToPanelBottom(JButton button){
		button.setFont(new Font("Aria", Font.ITALIC,fontSizeButton+3));
		this.panelBot.add(button);
	}
	
	public void updateInfo(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Arrays.asList(ChefLabel.values()).stream().forEach(e->{e.setValue("");});
				panelSx.removeAll();
				panelDx.removeAll();
				removeAll();
				list.clear();
				/*instanzio lista semicompleta*/
				list= (new InfoProjectImpl())
						.getSquadronSpecificInfo(squadName,unit.getContainers());
				/*riempio la enum*/
				Arrays.asList(ChefLabel.values()).stream().forEach(i->{
					i.setValue(list.stream().filter(e->e.getX().equals(i.getName())).findFirst()
							.orElse(new Pair<>(i.getName(), "non settato")).getY());});
				/*pulisco la lista, ci ricopio dentro l'enum e poi ri-inizializzo l'enum*/
				list.clear();
				Arrays.asList(ChefLabel.values()).stream().forEach(e->{
					list.add(new Pair<>(e.getName(), e.getValue()));
				});
				Arrays.asList(ChefLabel.values()).stream().forEach(e->{e.setValue("");});
				/*aggiungo elementi al pannello*/		
				list.stream().forEach(i->{
					if(isUsefull(i.getX())){	
						panelSx.add(createJLabel(i.getX(), i.getX(), fontSizeLabel));
						panelSx.add(createJLabel(i.getX()+"Val", i.getY(), fontSizeLabel));
						if(i.getX().equals("Capo: ") || i.getX().equals("Vice: ") ||i.getX().equals("Trice: ")){
							panelDx.add(instanceJButton(i));
						}
						else{
							panelDx.add(createJLabel("null", "",fontSizeLabel));
						}
					}
				});
			
				add(panelSx, BorderLayout.CENTER);
				add(panelDx, BorderLayout.EAST);
				add(panelBot, BorderLayout.SOUTH);
				repaint();
				validate();
			}
		});
		
		
		
	}

}
