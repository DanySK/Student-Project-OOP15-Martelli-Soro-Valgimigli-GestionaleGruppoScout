package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import view.general_utility.WarningNotice;
import view.gui_utility.MyJPanelImpl;

public class PanelCapiReparto extends MyJPanelImpl {
	
	private static final long serialVersionUID = 8502378638137835431L;
	private int fontSize=15;
	private final JTextField name=new JTextField();
	private final JTextField surname=new JTextField();
	private final JTextField mm=new JTextField();
	private final JTextField gg= new JTextField();
	private final JTextField aa= new JTextField();
	private final JTextField phone=new JTextField();

	private final ButtonGroup sex= new ButtonGroup();
	public PanelCapiReparto(String top){
		super(new BorderLayout());
		this.add(createJLabel("<html><U>"+top+"</U></html>", fontSize),BorderLayout.NORTH);
		MyJPanelImpl fields=new MyJPanelImpl(new GridLayout(0,2));
		
		fields.add(createJLabel( "Nome: ", fontSize));
		fields.add(name);
		fields.add(createJLabel( "Cognome: ", fontSize));
		fields.add(surname);
		fields.add(createJLabel( "Telefono: ", fontSize));
		fields.add(phone);
	
		
		MyJPanelImpl tmp;
		tmp=new MyJPanelImpl(new GridLayout(1, 6));
		tmp.add(this.createJLabel( "giorno", fontSize-5));
		tmp.add(gg);
		tmp.add(this.createJLabel( "mese", fontSize-5));
		tmp.add(mm);
		tmp.add(this.createJLabel( "anno", fontSize-5));
		tmp.add(aa);
		fields.add(createJLabel( "Data di nascita:", fontSize));
		fields.add(tmp);
		this.add(fields,BorderLayout.CENTER);
		
	}
	
	public String getNome(){
		return this.name.getText();
	}
	public String getSurname(){
		return this.surname.getText();
	}
	
	
	public LocalDate getDate(){
		try{
		LocalDate date= LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()),Integer.parseInt(gg.getText()));
		return date;
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return null;
	}
	public String getPhone(){
		return phone.getText();
	}
	
}
