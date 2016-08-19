package view.unit_manager.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import view.gui_utility.MyJPanelImpl;
import view.gui_utility.WarningNotice;

public class UnitLeaderJPanel extends MyJPanelImpl {

	private static final long serialVersionUID = 8502378638137835431L;
	private final static int FONTSIZE = 15;
	private final JTextField name = new JTextField();
	private final JTextField surname = new JTextField();
	private final JTextField mm = new JTextField();
	private final JTextField gg = new JTextField();
	private final JTextField aa = new JTextField();
	private final JTextField phone = new JTextField();
	private final JRadioButton sexM = new JRadioButton("Maschio");
	private final JRadioButton sexF = new JRadioButton("Femmina");
	private final ButtonGroup sexC = new ButtonGroup();
	private final MyJPanelImpl fields = new MyJPanelImpl(new GridLayout(0, 2));

	public UnitLeaderJPanel(final String top) {
		super(new BorderLayout());
		this.add(createJLabel("<html><U>" + top + "</U></html>", FONTSIZE), BorderLayout.NORTH);

		fields.add(createJLabel("Nome: ", FONTSIZE));
		fields.add(name);
		fields.add(createJLabel("Cognome: ", FONTSIZE));
		fields.add(surname);
		fields.add(createJLabel("Telefono: ", FONTSIZE));
		fields.add(phone);

		final MyJPanelImpl tmp;
		tmp = new MyJPanelImpl(new GridLayout(1, 6));
		tmp.add(this.createJLabel("giorno", FONTSIZE - 5));
		tmp.add(gg);
		tmp.add(this.createJLabel("mese", FONTSIZE - 5));
		tmp.add(mm);
		tmp.add(this.createJLabel("anno", FONTSIZE - 5));
		tmp.add(aa);
		fields.add(createJLabel("Data di nascita:", FONTSIZE));
		fields.add(tmp);
		this.add(fields, BorderLayout.CENTER);

	}

	public String getNome() {
		return this.name.getText();
	}

	public String getSurname() {
		return this.surname.getText();
	}

	public LocalDate getDate() {
		try {
			return LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()),
					Integer.parseInt(gg.getText()));
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
		}
		return null;
	}

	public String getPhone() {
		return phone.getText();
	}

	public void addSexChoose() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				final MyJPanelImpl sex = new MyJPanelImpl();
				sexC.add(sexM);
				sexC.add(sexF);
				sex.add(sexM);
				sex.add(sexF);
				fields.add(fields.createJLabel("Sesso", FONTSIZE));
				fields.add(sex);
				fields.validate();
				fields.repaint();
				validate();
				repaint();
				
			}
		});
	}

	public boolean isSex() {
		return sexM.isSelected();
	}

}
