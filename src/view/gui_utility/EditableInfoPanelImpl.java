package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import model.Roles;
import model.Squadron;
import view.general_utility.WarningNotice;
/**
 * 
 * @author Giovanni Martelli
 *
 */
public class EditableInfoPanelImpl extends MyJPanelImpl {
	
	private static final long serialVersionUID = 3525477272751960285L;

	/*
	 * Enum contenente le voci per le info riguardanti la squadriglia
	 */
	enum ChefLabel {
		Nome("Nome: ", ""),
		Sesso("Sesso: ", ""),
		Capo("Capo: ", ""),
		Vice("Vice: ", ""),
		Trice("Trice: ", ""),
		Membri("Numero di membri: ", "");
		private final String name;
		private String value;
		
		public String getName() { return this.name; }
		public String getValue() { return this.value; }
		public void setValue(final String valueParam) { this.value = valueParam; }
		ChefLabel(final String nameParam, final String valueParam) {
			this.name = nameParam;
			this.value = valueParam;
		}
	}
	
	private final String squadName;
	private final boolean editable;
	private final int fontSizeButton;
	private final int fontSizeLabel;
	private List<Pair<String, String>> list;
	private final MyJPanelImpl panelSx;
	private final MyJPanelImpl panelDx;
	private final MyJPanelImpl panelBot;
	private final Unit unit;
	private final Squadron squadImpl;
	/**Creates a JPanel for info 
	 * 
	 * @param squad
	 * @param isManager
	 * @param fontLabel
	 * @param fontButton
	 */
	public EditableInfoPanelImpl(final String squad, final boolean isManager, final int fontLabel, final int fontButton) {
		super(new BorderLayout());
		this.editable = isManager;
		this.fontSizeLabel = fontLabel;
		this.fontSizeButton = fontButton;
		this.unit = MyJFrameSingletonImpl.getInstance().getUnit();
		this.list = new ArrayList<>();
		panelSx = new MyJPanelImpl(new GridLayout(0, 2));
		panelDx = new MyJPanelImpl(new GridLayout(0, 1));
		panelBot = new MyJPanelImpl(new GridLayout(0, 3));
		this.squadName = squad;
		this.squadImpl=unit.getContainers().findSquadron(squadName);
		
		/*
		 * Aggiungo il panello con tutte le info
		 */
		this.updateInfo();
		/*
		 * Aggiung i bottoni Cancelleria, Batteria e Cassa
		 * 
		 */
		list.add(new Pair<>("Cancelleria", squadImpl.getNoteCancelleria()));
		list.add(new Pair<>("Batteria", squadImpl.getNoteBatteria()));
		list.add(new Pair<>("Cassa", squadImpl.getNoteCassa()));
		list.stream().forEach(e-> {
			panelBot.add(createButton("Note " + e.getX().toLowerCase(), k-> {
				JDialog dial = new JDialog();
				JPanel panel = new JPanel(new BorderLayout());
				JPanel bot = new JPanel();
				JTextArea area = createJTextArea( e.getY(), isManager, fontLabel);
				panel.add(area);
				if( editable ) {
					bot.add(createButton("Ok", t->{
						try { /*Utilizzo la reflection*/
							Method m = squadImpl.getClass().getDeclaredMethod("setNote" + e.getX(), String.class);
							m.invoke(squadImpl, area.getText());/*chiamata al metodo trovato*/
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
				panel.add(createJLabel( "<html><U>"+(!editable?"Note ":"Modifica note ")+
						e.getX().toLowerCase()+"</U></html>", fontLabel), BorderLayout.NORTH);
				panel.add(bot, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(null);
				dial.setVisible(true);
			}));
		});
		for(Component i: panelBot.getComponents()){
			i.setFont( new Font("Aria", Font.ITALIC,fontButton+3));
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
				JTextArea area= createJTextArea( "", false, 18);
				String k = i.getY();
				List<Pair<String,String>> areaText= (new InfoProjectImpl()).getMemberSpecificalInfo(unit.getContainers().getMember(k.substring(0, k.indexOf(" ")), k.substring(k.indexOf(" ")+1)));
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
			Map<Member,Roles> tmp = squadImpl.getMembri();
			
		
			MyJPanelImpl panelExternal=new MyJPanelImpl(new BorderLayout());
			MyJPanelImpl panelBotExternal=new MyJPanelImpl();
			JPanel panelCenterExternal=new JPanel(new GridLayout(2,2));
			
			panelCenterExternal.add(panelBotExternal.createJLabel( "Nome:", fontSizeLabel));
			JTextField nome=new JTextField();
			panelCenterExternal.add(nome);
			panelCenterExternal.add(panelBotExternal.createJLabel("Cognome:", fontSizeLabel));
			JTextField cognome=new JTextField();
			panelCenterExternal.add(cognome);
			
			
			
			
			panelBotExternal.add(panelBotExternal.createButton("Cerca",k->{
				
				//se non trovo nessun member salvato con quel nome e cognome avviso utente
				
				List<Member>listChef= (!nome.getText().isEmpty()&&!cognome.getText().isEmpty())?
						tmp.keySet().stream().filter(t->t.getName().equals(nome.getText())&&t.getSurname().equals(cognome.getText()))
									.collect(Collectors.toList())
						:(!nome.getText().isEmpty())?tmp.keySet().stream().filter(t->t.getName().equals(nome.getText()))
									.collect(Collectors.toList())
									:tmp.keySet().stream().filter(l->l.getSurname().equals(cognome.getText())).collect(Collectors.toList());
				
				if(listChef.isEmpty()){
					new WarningNotice("<html>Nessun membro trovato con quel nome.<br>"
							+ "Controllare i dati inseriti e riprovare</html>");
				}
				else{
					JDialog dialInternal=new JDialog();
					MyJPanelImpl panInternal=new MyJPanelImpl(new BorderLayout());
					panInternal.add(panInternal.createJLabel( "Scegliere la corrispondenza desiderata", fontSizeLabel),BorderLayout.NORTH);
					MyJPanelImpl panMember= new MyJPanelImpl(new GridLayout(0, 1));
					MyJPanelImpl paneSelect=new MyJPanelImpl(new GridLayout(0,1));
					MyJPanelImpl panBot=new MyJPanelImpl(new BorderLayout());
					panBot.add(panBot.createButton("Annulla", u->{
						dialInternal.dispose();
					}),BorderLayout.EAST);
					panInternal.add(panBot,BorderLayout.SOUTH);
					panInternal.add(panMember,BorderLayout.CENTER);
					panInternal.add(paneSelect,BorderLayout.EAST);
					listChef.stream().forEach(p->{
							panMember.add(panMember.createJTextArea("Nome: "+p.getName()+System.lineSeparator()+
								"Cognome: "+p.getSurname()+System.lineSeparator()+
								"Data di nascita: "+p.getBirthday().toString(), false, fontSizeLabel));
							paneSelect.add(paneSelect.createButton("Scegli", o->{
								
								dialInternal.dispose();
								String method="set"+i.getX().substring(0, i.getX().length()-2)+((i.getX().equals("Vice: "))?"capoSq":"Sq");
								try {
									if(i.getX().equals("Capo: ") && squadImpl.isCapoPresent()){
										squadImpl.removeCapo();
									}
									else if(i.getX().equals("Vice: ") && squadImpl.isVicecapoPresent()){
										squadImpl.removeVice();
									}
									else{
										if(squadImpl.isTricecapoPresent())squadImpl.removeTrice();
									}
									Method mt=unit//ricavo metodo
											.getContainers().findSquadron(squadName).getClass().getDeclaredMethod(method, Member.class);
									//lancio metodo
									mt.invoke(squadImpl,p);
									MyJFrameSingletonImpl.getInstance().setNeedToSave();//setto che sono avvenuti dei cambiamenti
									new WarningNotice("<html>Membro trovato e settato come "+i.getX().toLowerCase()+"<br>"
											+"Ricordati di salvare o perderai le modifiche </html>");
									updateInfo();
						
						}catch(Exception w){
							
						}
												
							}));
						
					});
					
					dialInternal.add(panInternal);
					dialInternal.pack();
					dialInternal.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
					dialInternal.setVisible(true);
							
				}
				
				}));
			panelBotExternal.add(panelBotExternal.createButton("Annulla", t->dial.dispose()));
			panelExternal.add(panelCenterExternal, BorderLayout.CENTER);
			panelExternal.add(panelBotExternal,BorderLayout.SOUTH);
			panelExternal.add(panelBotExternal.createJLabel( "<html><U><CENTER>Assegna carica</CENTER></U><br>"
					+ "Per effettuare la ricerca inserire almeno uno dei due campi richiesti</html>", fontSizeLabel),BorderLayout.NORTH);
				dial.add(panelExternal);
					
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);
			
		
	});
	}
	public void addButtonToPanelBottom(JButton button){
		button.setFont(new Font("Aria", Font.ITALIC,fontSizeButton+3));
		this.panelBot.add(button);
	}
	
	private void updateInfo(){
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
						panelSx.add(createJLabel( i.getX(), fontSizeLabel));
						panelSx.add(createJLabel( i.getY(), fontSizeLabel));
						if(i.getX().equals("Capo: ") || i.getX().equals("Vice: ") ||i.getX().equals("Trice: ")){
							panelDx.add(instanceJButton(i));
						}
						else{
							panelDx.add(createJLabel( "",fontSizeLabel));
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
