package view.fee_manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.myUtil.MyOptional;
import model.reparto.Member;
import view.Main.MyColor;
import view.gui_utility.EditableElementScrollPanelImpl;
import view.gui_utility.EditableElementScrollPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.WarningNotice;

public class UnitFeeManager {

	private final String repName;
	private final Unit unit;

	public UnitFeeManager(final String name) {
		this.repName = name;
		this.unit = MyJFrameSingletonImpl.getInstance().getUnit();

	}

	public class GestioneTasseRepartoImplPane extends MyJPanelImpl {
		private static final long serialVersionUID = -3959125437548824576L;
		private final static int FONTSIZE = 19;
		private final static int FONTSIZELABEL = 15;
		private final static int FONTSIZELABELDATE = 16;
		private final MyJPanelImpl infoLeft;
		private final MyJPanelImpl infoRight;
		private final MyJPanelImpl center;
		private final MyJPanelImpl infoLeContainer;

		@SuppressWarnings("unchecked")
		public GestioneTasseRepartoImplPane() {
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione tasse " + repName + "</U></html>", FONTSIZE), BorderLayout.NORTH);
			this.center = new MyJPanelImpl(new BorderLayout());
			this.infoLeft = new MyJPanelImpl(new GridLayout(0, 2));
			this.infoRight = new MyJPanelImpl(new GridLayout(0, 1));
			this.infoLeContainer = new MyJPanelImpl(new BorderLayout());
			infoLeContainer.add(infoLeft, BorderLayout.CENTER);
			infoLeContainer.add(infoRight, BorderLayout.EAST);
			infoLeft.add(createJLabel("Limite pagamento retta:", FONTSIZELABELDATE));
			infoLeft.add(
					createJLabel(MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getDateToPay().toString(),
							FONTSIZE - 3));
			infoRight.add(createButton("Cambia", e -> {
				final JDialog dial = new JDialog();
				final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
				panel.add(createJLabel("<html><U>Nuova data", FONTSIZE));
				final MyJPanelImpl panelInternal = new MyJPanelImpl();
				final JTextField anno = new JTextField();
				final JTextField mese = new JTextField();
				final JTextField giorno = new JTextField();
				final MyJPanelImpl date = new MyJPanelImpl(new GridLayout(1, 6));
				date.add(createJLabel("giorno", FONTSIZELABEL));
				date.add(giorno);
				date.add(createJLabel("mese", FONTSIZELABEL));
				date.add(mese);
				date.add(createJLabel("anno", FONTSIZELABEL));
				date.add(anno);
				panel.add(date, BorderLayout.CENTER);
				panelInternal.add(createButton("Salva", k -> {
					try {
						unit.getReparto().setDateToPay(LocalDate.of(Integer.parseInt(anno.getText()),
								Integer.parseInt(mese.getText()), Integer.parseInt(giorno.getText())));
						MyJFrameSingletonImpl.getInstance().setNeedToSave();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								infoLeft.removeAll();
								infoLeft.add(createJLabel("Limite pagamento retta:", FONTSIZELABELDATE));
								infoLeft.add(createJLabel(MyJFrameSingletonImpl.getInstance().getUnit().getReparto()
										.getDateToPay().toString(), FONTSIZE - 3));
								infoLeft.add(new JLabel());
								infoLeft.add(new JLabel());
								infoLeft.validate();
								center.validate();
							}
						});
						dial.dispose();
					} catch (Exception o) {
						new WarningNotice(o.getMessage());
					}
				}));
				panelInternal.add(createButton("Annulla", p -> {
					dial.dispose();
				}));
				panel.add(panelInternal, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);
			}));
			this.infoLeft.add(new JLabel());
			this.infoLeft.add(new JLabel());
			this.infoRight.add(new JLabel());
			this.center.add(infoLeContainer, BorderLayout.NORTH);
			center.add(new EditableElementScrollPanelImpl<Member>(Type.RETTAREP, MyOptional.empty()));
			this.add(center, BorderLayout.CENTER);
			this.infoLeContainer.add(
					createJLabel("<html><U>Membri che non hanno pagato la tassa annuale</U></html>", FONTSIZE),
					BorderLayout.SOUTH);
			((JLabel) this.infoLeContainer.getComponent(2))
					.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, MyColor.BLACK.get()));
			((EditableElementScrollPanelImpl<Member>) this.center.getComponent(1))
					.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, MyColor.BLACK.get()));

		}
	}

	public String toString() {
		return "Quota Annuale";
	}

}
