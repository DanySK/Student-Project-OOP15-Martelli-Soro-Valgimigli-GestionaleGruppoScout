package view.gestione_tasse.utility;

import java.awt.BorderLayout;
import java.time.Year;

import javax.swing.JDialog;

import model.reparto.Member;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class MemberTasseJDialog extends JDialog {

	private static final long serialVersionUID = -3887913606041748337L;
	private final static int FONTSIZE = 19;

	public MemberTasseJDialog(final Member mem, final EditableMemberPanelImpl<Member> parent) {
		super();
		final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl panBot = new MyJPanelImpl();
		panel.add(panel.createJLabel("<html><U>Pagamento Membro</U></html>", FONTSIZE), BorderLayout.NORTH);
		panel.add(
				panel.createJLabel("<html>Al momento " + mem.getName() + " " + mem.getSurname()
						+ " non ha pagato<br>nessuna quota di partecipazione agli Scout.<br>"
						+ "Cliccando su \"Paga\" verrà registrata<br>la quota come pagata.", FONTSIZE),
				BorderLayout.CENTER);
		panBot.add(panel.createButton("Paga", u -> {
			mem.setTax(Year.now().getValue());
			MyJFrameSingletonImpl.getInstance().setNeedToSave();
			parent.updateMember();
			this.dispose();
		}));
		panBot.add(panel.createButton("Annulla", u -> this.dispose()));
		panel.add(panBot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
	}
}
