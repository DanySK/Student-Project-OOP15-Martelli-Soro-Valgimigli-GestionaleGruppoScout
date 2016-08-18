package view.gestione_eventi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import control.SortExcursionImpl;
import control.myUtil.MyOptional;
import model.Excursion;
import model.Squadron;
import model.UscitaSquadriglia;
import view.gestione_eventi.utility.AddExcursionJDialog;
import view.gestione_eventi.utility.AddExcursionJDialog.TypeExcursion;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.SearchElementJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;

public class EventiSquadriglia {
	private final Squadron squadImpl;

	public EventiSquadriglia(final String squad) {
		squadImpl = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squad);
	}

	public class EventiSquadrigliaPane extends MyJPanelImpl {

		private static final long serialVersionUID = 5205825583794848349L;
		private final static int FONTSIZELABEL = 19;
		private final static int FONTSIZELABELBIG = 21;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelTopContainer;
		private final MyJPanelImpl panelTopInfo;
		private final MyJPanelImpl panelTopButton;
		private final EditableMemberPanelImpl<Excursion> panelBot;

		public EventiSquadrigliaPane() {
			super(new BorderLayout());

			this.add(createJLabel("<html><U>Gestione eventi " + squadImpl.getNome() + "</U></html> ", FONTSIZELABELBIG),
					BorderLayout.NORTH);
			this.panelCenter = new MyJPanelImpl(new GridLayout(2, 1));
			this.panelTopInfo = new MyJPanelImpl(new GridLayout(0, 2));
			this.updatePaneInfo();
			this.panelTopButton = new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
			this.panelTopContainer = new MyJPanelImpl(new BorderLayout());
			this.panelTopContainer.add(panelTopInfo, BorderLayout.CENTER);
			this.panelCenter.add(panelTopContainer);
			this.panelBot = new EditableMemberPanelImpl<Excursion>(Type.EXCSQUAD, MyOptional.empty());
			panelTopButton.add(createButton("<html>Aggiungi<br>Uscita</html>", 12, e -> {
				new AddExcursionJDialog(TypeExcursion.Uscita_Squadriglia, MyOptional.of(squadImpl.getNome()), this);
			}));

			panelTopButton.add(createButton("<html>Rimuovi<br>Uscita</html>", 12, e -> {
				new SearchElementJDialog<>(SearchType.removeExcursion, MyOptional.empty(), MyOptional.empty(), this);

			}));
			this.panelTopContainer.add(panelTopButton, BorderLayout.SOUTH);

			this.panelCenter.add(panelBot);
			this.add(panelCenter, BorderLayout.CENTER);
		}

		public final void updatePaneInfo() {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					panelTopInfo.removeAll();
					// Long i =1 ;
					panelTopInfo.add(createJLabel("Prossimo Uscita: ", FONTSIZELABEL));

					panelTopInfo.add(createJLabel((new SortExcursionImpl())
							.sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
									.getExcursion().stream().filter(e -> e instanceof UscitaSquadriglia)
									.collect(Collectors.toList()))
							.stream().map(t -> new String(t.getName() + "(" + t.getDateStart() + ")")).findFirst()
							.orElse("Niente in programma"), FONTSIZELABEL));
					panelTopInfo.repaint();
					panelTopInfo.validate();

				}
			});

		}

		public void updateEventi() {
			panelBot.updateMember();
		}
	}

	public String toString() {
		return "Uscite " + squadImpl.getNome();
	}

}
