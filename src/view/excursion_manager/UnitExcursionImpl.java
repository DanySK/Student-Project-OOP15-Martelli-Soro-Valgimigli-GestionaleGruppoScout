package view.excursion_manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import control.SortExcursionImpl;
import control.myUtil.MyOptional;
import model.escursioni.Campo;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.escursioni.Uscita;
import view.excursion_manager.utility.AddExcursionJDialog;
import view.excursion_manager.utility.AddExcursionJDialog.TypeExcursion;
import view.gui_utility.EditableElementScrollPanelImpl;
import view.gui_utility.EditableElementScrollPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.SearchElementJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;

public class UnitExcursionImpl {
	/**
	 * Class that allows to see and edit information about Unit's Excursions
	 * @author Giovanni Martelli
	 */
	public class UnitExcursionPaneImpl extends MyJPanelImpl implements UnitExcursionPane {

		private static final long serialVersionUID = 5205825583794848349L;
		private final static int FONTSIZELABEL = 19;
		private final static int FONTSIZELABELBIG = 21;
		private final static int FONTSIZEBUTTON = 12;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelTopContainer;
		private final MyJPanelImpl panelTopInfo;
		private final MyJPanelImpl panelTopButton;
		private final EditableElementScrollPanelImpl<Excursion> panelBot;
		private final static String NOTHING = "Niente in programma";

		public UnitExcursionPaneImpl() {
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione eventi reparto</U></html> ", FONTSIZELABELBIG),
					BorderLayout.NORTH);
			this.panelCenter = new MyJPanelImpl(new GridLayout(2, 1));
			this.panelTopInfo = new MyJPanelImpl(new GridLayout(0, 2));
			this.updatePaneInfo();
			this.panelTopButton = new MyJPanelImpl();
			this.panelTopContainer = new MyJPanelImpl(new BorderLayout());
			this.panelTopContainer.add(panelTopInfo, BorderLayout.CENTER);
			
			this.panelCenter.add(panelTopContainer);
			this.panelBot = new EditableElementScrollPanelImpl<Excursion>(Type.UNITEXC, MyOptional.empty());
			panelTopButton.add(createButton("<html>Aggiungi<br>Campo</html>", FONTSIZEBUTTON, e -> {
				new AddExcursionJDialog(TypeExcursion.Campo, MyOptional.empty(), this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Evento di zona", FONTSIZEBUTTON, e -> {
				new AddExcursionJDialog(TypeExcursion.Evento_di_Zona, MyOptional.empty(), this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Gemellaggio", FONTSIZEBUTTON, e -> {
				new AddExcursionJDialog(TypeExcursion.Gemellaggio, MyOptional.empty(), this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Uscita", FONTSIZEBUTTON, e -> {
				new AddExcursionJDialog(TypeExcursion.Uscita, MyOptional.empty(), this);
			}));
			panelTopButton.add(createButton("<html>Rimuovi<br>Escursione</html>", FONTSIZEBUTTON, e -> {
				new SearchElementJDialog<>(SearchType.removeExcursion, MyOptional.empty(), MyOptional.empty(), this);
			}));
			this.panelTopContainer.add(panelTopButton, BorderLayout.SOUTH);

			this.panelCenter.add(panelBot);
			this.add(panelCenter, BorderLayout.CENTER);
		}
		/* (non-Javadoc)
		 * @see view.gestione_eventi.UnitExcursion#updatePaneInfo()
		 */

		@Override
		public final void updatePaneInfo() {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					panelTopInfo.removeAll();
					// Long i =1 ;
					panelTopInfo.add(createJLabel("Prossimo Campo: ", FONTSIZELABEL));
					panelTopInfo
							.add(createJLabel(
									(new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance()
											.getUnit().getContainers().getExcursion().stream()
											.filter(e -> e instanceof Campo).collect(Collectors.toList()))).stream()
													.map(t -> new String(t.getName() + "(" + t.getDateStart() + ")"))
													.findFirst().orElse(NOTHING),
									FONTSIZELABEL));

					panelTopInfo.add(createJLabel("Prossimo Evento: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel(
							(new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit()
									.getContainers().getExcursion().stream().filter(e -> e instanceof EventiDiZona)
									.collect(Collectors.toList()))).stream()
											.map(t -> new String(t.getName() + "(" + t.getDateStart() + ")"))
											.findFirst().orElse(NOTHING),
							FONTSIZELABEL));

					panelTopInfo.add(createJLabel("Prossimo Gemellaggio: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel((new SortExcursionImpl().sortByDateOfStart(
							MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
									.filter(e -> e instanceof Gemellaggi).collect(Collectors.toList()))).stream()
											.map(t -> new String(t.getName() + "(" + t.getDateStart() + ")"))
											.findFirst().orElse(NOTHING),
							FONTSIZELABEL));

					panelTopInfo.add(createJLabel("Prossima Uscita: ", FONTSIZELABEL));
					panelTopInfo
							.add(createJLabel(
									(new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance()
											.getUnit().getContainers().getExcursion().stream()
											.filter(e -> e instanceof Uscita).collect(Collectors.toList()))).stream()
													.map(t -> new String(t.getName() + "(" + t.getDateStart() + ")"))
													.findFirst().orElse(NOTHING),
									FONTSIZELABEL));
					panelTopInfo.repaint();
					panelTopInfo.validate();

				}
			});

		}
		
		@Override
		public void updateExcursion() {
			panelBot.updateMember();
		}

	}
	@Override
	public String toString() {
		return "Uscite Reparto";
	}
}
