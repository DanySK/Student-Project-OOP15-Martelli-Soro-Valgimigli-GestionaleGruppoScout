package view.gestione_reparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import control.myUtil.MyOptional;
import model.exception.IllegalOperationException;
import model.reparto.Member;
import view.general_utility.WarningNotice;
import view.gestione_reparto.utility.JTextAreaDialog.OB;
import view.gestione_reparto.utility.JTextAreaDialog.TextAreaType;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class MemberPathJDialog extends JDialog {

	private static final long serialVersionUID = 2106257742400385767L;
	private final static int FONTSIZE = 18;
	private final static String VEDI = "Vedi";
	private final static String EDIT = "Edit";

	public MemberPathJDialog(final Member mem, final boolean editable) {
		super();
		final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl internal = new MyJPanelImpl(new GridLayout(0, 1));
		final MyJPanelImpl right = new MyJPanelImpl(new GridLayout(0, 1));
		final MyJPanelImpl bot = new MyJPanelImpl();

		if (!editable) {
			internal.add(panel.createJLabel("Livello", FONTSIZE));
			right.add(panel.createJLabel(mem.getPath().getLevel(), FONTSIZE));
			right.add(panel.createButton(VEDI, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.FM));
			}));
			right.add(panel.createButton(VEDI, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.SCL));
			}));
			right.add(panel.createButton(VEDI, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.FD));
			}));
			right.add(panel.createButton(VEDI, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.RLZN));
			}));
		} else {
			internal.add(panel.createJLabel("Livello", FONTSIZE));
			right.add(panel.createButton(EDIT, FONTSIZE, e -> {
				final JDialog dial = new JDialog();
				final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
				final MyJPanelImpl center = new MyJPanelImpl(new GridLayout(1, 2));
				final MyJPanelImpl botIn = new MyJPanelImpl();
				final JLabel label = pan.createJLabel("Livello: " + mem.getPath().getLevel(), FONTSIZE);
				botIn.add(bot.createButton("OK", k -> {
					dial.dispose();
				}));
				center.add(center.createButton("Level UP", f -> {
					try {
						mem.getPath().livUp();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								label.setText("Livello: " + mem.getPath().getLevel());
								pan.validate();
								pan.repaint();
							}
						});
						MyJFrameSingletonImpl.getInstance().setNeedToSave();
					} catch (IllegalOperationException e1) {
						new WarningNotice(e1.getMessage());
					}

				}));
				center.add(center.createButton("Level DOWN", f -> {
					try {
						mem.getPath().livDown();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								label.setText("Livello: " + mem.getPath().getLevel());
								pan.validate();
								pan.repaint();
							}
						});
						MyJFrameSingletonImpl.getInstance().setNeedToSave();
					} catch (IllegalOperationException e1) {
						new WarningNotice(e1.getMessage());
					}

				}));
				pan.add(label, BorderLayout.NORTH);
				pan.add(center, BorderLayout.CENTER);
				pan.add(botIn, BorderLayout.SOUTH);
				dial.add(pan);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);

			}));
			right.add(panel.createButton(EDIT, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.FM));
			}));
			right.add(panel.createButton(EDIT, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.SCL));
			}));
			right.add(panel.createButton(EDIT, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.FD));
			}));
			right.add(panel.createButton(EDIT, FONTSIZE, e -> {
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.RLZN));
			}));
		}
		bot.add(bot.createButton("OK", FONTSIZE, e -> {
			this.dispose();
		}));
		panel.add(panel.createJLabel("<html></U>Cammino Membro</U></html>", FONTSIZE), BorderLayout.NORTH);
		internal.add(panel.createJLabel("Famiglia", FONTSIZE));
		internal.add(panel.createJLabel("Scuola", FONTSIZE));
		internal.add(panel.createJLabel("Fede", FONTSIZE));
		internal.add(panel.createJLabel("Relazioni", FONTSIZE));
		panel.add(internal, BorderLayout.WEST);
		panel.add(right, BorderLayout.EAST);
		panel.add(bot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
	}
}
