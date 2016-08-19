package view.gestione_reparto.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.myUtil.MyOptional;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
/**
 * Class tha create a JTextArea for "Specialità" and "Obbiettivi del cammino
 * based on TextAreaType and OB it allows user to see or modify this entrys
 * @author Giovanni Martelli
 *
 * @param <E>
 */
public class JTextAreaDialog<E> extends JDialog {
	public enum TextAreaType {
		SPCVIEW,//vedi specialità
		SPEDIT,//modifica specialità
		OBBVIEW,//vedi obbiettivo
		OBBEDIT;//modifica obbiettivo
	}

	public enum OB {
		SCL,//scuola
		FD, //fede
		FM,//famiglia
		RLZN;//relazioni
	}

	private static final long serialVersionUID = -8715597288634279098L;
	private final static int FONTSIZE = 19;
	private final static int FONTSIZEAREA = 17;
	private final TextAreaType type;
	private JTextArea area;
	private final Member elem;
	private OB ob;
	private final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
	/**
	 * 
	 * @param type Type
	 * @param m element
	 * @param obt
	 */
	public JTextAreaDialog(final TextAreaType type, final E m, final MyOptional<OB> obt) {
		super();
		this.type = type;
		this.elem = (Member)m;
		final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl inPanel = new MyJPanelImpl(new GridLayout(2, 1));
		final MyJPanelImpl panelBot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
		if (obt.isPresent()) {
			ob = obt.get();
		}
		getArea();
		final JScrollPane scroll = new JScrollPane(area);
		inPanel.add(this.getLabelTop());
		inPanel.add(scroll);

		if (getAnnullaButton().isPresent()) {
			panelBot.add(getAnnullaButton().get());
		}
		panelBot.add(panel.createButton("Ok", getOkActionListJButton()));
		panel.add(inPanel, BorderLayout.CENTER);
		panel.add(panelBot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);

	}

	private JLabel getLabelTop() {
		if (type.equals(TextAreaType.SPCVIEW)) {
			return panel.createJLabel("Specialità del Membro", FONTSIZE);
		} else if (type.equals(TextAreaType.SPEDIT)) {
			return panel.createJLabel("<html><U>Specialità del Membro</U><br>"
					+ "Aggiungere specialità, separandole dalle altre con il tasto \"INVIO\"</html>", FONTSIZE);
		} else if (type.equals(TextAreaType.OBBEDIT)) {
			return panel.createJLabel("Modifica Obbiettivo", FONTSIZE);
		} else {
			return panel.createJLabel("Obbiettivo", FONTSIZE);
		}
	}

	private void getArea() {
		area = panel.createJTextArea("", false, FONTSIZEAREA);
		if (type.equals(TextAreaType.SPCVIEW) || type.equals(TextAreaType.SPEDIT)) {
			((Member) elem).getSpecialities().stream().forEach(e -> {
				area.append(e);
			});
		} else {
			if (ob.equals(OB.FD)) {
				area.setText((((Member) elem).getPath().getFaith()));
			} else if (ob.equals(OB.SCL)) {
				area.setText(((Member) elem).getPath().getSchool());
			} else if (ob.equals(OB.FM)) {
				area.setText(((Member) elem).getPath().getFamily());
			} else {
				area.setText(((Member) elem).getPath().getRelations());
			}
		}
		if (type.equals(TextAreaType.SPEDIT) || type.equals(TextAreaType.OBBEDIT)) {
			area.setEditable(true);
		}

	}

	private ActionListener getOkActionListJButton() {
		if (type.equals(TextAreaType.SPCVIEW) || type.equals(TextAreaType.OBBVIEW)) {
			return e -> dispose();
		} else if (type.equals(TextAreaType.SPEDIT)) {
			return e -> {
				((Member) elem).getSpecialities().stream().forEach(j -> {
					try {
						((Member) elem).removeSpecialities(j);
					} catch (ObjectNotContainedException e1) {
						new WarningNotice(e1.getMessage());
					}

				});
				if (!area.getText().isEmpty()) {
					Arrays.asList(area.getText().split(System.lineSeparator())).stream().forEach(t -> {
						try {
							((Member) elem).addSpecialities(t);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});
					MyJFrameSingletonImpl.getInstance().setNeedToSave();
				}
				dispose();
			};
		} else {
			return e -> {
				if (ob.equals(OB.FD)) {
					((Member) elem).getPath().setFaith(area.getText());
				} else if (ob.equals(OB.FM)) {
					((Member) elem).getPath().setFamily(area.getText());
				} else if (ob.equals(OB.SCL)) {
					((Member) elem).getPath().setSchool(area.getText());
				} else {
					((Member) elem).getPath().setRelations(area.getText());
				}
				MyJFrameSingletonImpl.getInstance().setNeedToSave();
				dispose();
			};
		}
	}

	private MyOptional<JButton> getAnnullaButton() {
		if (type.equals(TextAreaType.SPEDIT) || type.equals(TextAreaType.OBBEDIT)) {
			return MyOptional.of(panel.createButton("Annulla", e -> dispose()));
		} else {
			return MyOptional.empty();
		}
	}
}
