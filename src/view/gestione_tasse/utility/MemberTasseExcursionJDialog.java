package view.gestione_tasse.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.escursioni.Excursion;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import view.general_utility.WarningNotice;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class MemberTasseExcursionJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2645669972966333035L;
	private final static int FONTSIZE = 19;
	private final List<Excursion> list;
	private final Member me;

	private final MyJPanelImpl memPane;
	private final JScrollPane scroll;

	public MemberTasseExcursionJDialog(final Member me, final EditableMemberPanelImpl<Member> parent) {
		super();
		this.me = me;
		final JTextArea area;
		this.list = new ArrayList<>();
		memPane = new MyJPanelImpl();

		scroll = new JScrollPane(memPane);
		memPane.setPreferredSize(scroll.getPreferredSize());
		final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl panelCentral = new MyJPanelImpl(new GridLayout(2, 1));

		final MyJPanelImpl panBot = new MyJPanelImpl();
		panel.add(panelCentral.createJLabel(
				"<html><U>Pagamenti Escursioni " + me.getName() + " " + me.getSurname() + "</U></html>", FONTSIZE),
				BorderLayout.NORTH);
		area = panel.createJTextArea("Questo membro non ha pagato le seguenti escursioni;"
				+ "\ncliccare su un pulsante per pagare l'escursione", false, FONTSIZE - 2);
		area.setEditable(false);
		updateEscursion();

		panBot.add(panelCentral.createButton("Paga Tutte", u -> {
			list.stream().forEach(e -> {
				try {
					e.setPaied(me);
					parent.updateMember();
				} catch (ObjectNotContainedException e1) {
					new WarningNotice(e1.getMessage());
				}
			});
			dispose();
		}));
		panelCentral.add(area);
		panelCentral.add(memPane);
		panBot.add(panelCentral.createButton("OK", u -> {
			parent.updateMember();
			this.dispose();

		}));
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(panBot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
	}

	private void updateEscursion() {

		list.clear();
		MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream().forEach(e -> {
			if (e.getNotPaied().contains(me)) {
				list.add(e);
			}
		});
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				memPane.removeAll();
				list.stream().forEach(e -> {
					memPane.add(memPane.createButton(e.getName(), k -> {
						try {
							e.setPaied(me);
							new WarningNotice("Pagamento registrato");

						} catch (ObjectNotContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
						updateEscursion();

					}));
				});
				memPane.validate();
				memPane.repaint();
				scroll.revalidate();
				scroll.repaint();

			}
		});
	}
}
