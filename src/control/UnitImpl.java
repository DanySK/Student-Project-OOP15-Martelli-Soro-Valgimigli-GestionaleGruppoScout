package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import control.exception.MemberSexException;
import control.myUtil.Pair;
import extra.mail.ControlMail;
import model.escursioni.Excursion;
import model.escursioni.UscitaSquadriglia;
import model.escursioni.UscitaSquadrigliaImpl;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import model.reparto.Reparto;
import model.reparto.Roles;
import model.reparto.Squadron;
import view.gui_utility.WarningNotice;

public class UnitImpl implements Unit, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4391311361473527351L;
	final private Reparto rep;
	final private List<Excursion> excursions;
	private LocalDate lastMailSend = LocalDate.now();

	public UnitImpl(final Reparto rep) {
		this.excursions = new ArrayList<>();
		this.rep = rep;
	}

	@Override
	public String getName() {
		return this.rep.getName().replaceAll(" ", "_");
	}

	@Override
	public Container getContainers() {
		final Container container = new ContainerImpl(this.rep.getAllSquadron(), this.rep.getMembriSenzaSquadriglia(),
				this.excursions);
		return container;
	}

	@Override
	public void setName(final String name) {
		this.rep.setName(name);
	}

	@Override
	public String info() {
		String info = "";
		info += "Nome reparto: " + this.rep.getName() + "\n";
		info += "Capo maschio: " + this.rep.getCapoM().getName() + "\n";
		info += "Capo femmina: " + this.rep.getCapoF().getName() + "\n";
		info += "Number of members: " + this.getContainers().getMembers().size() + "\n";
		info += "Number of squadrons: " + this.getContainers().getSquadrons().size() + "\n";
		return info;
	}

	@Override
	public List<Pair<String, String>> getUnitSpecificInfo() {
		final List<Pair<String, String>> info = new ArrayList<>();

		info.add(new Pair<>("Name", this.getName()));
		info.add(new Pair<>("Number of member", Integer.toString(this.getContainers().getMembers().size())));
		info.add(new Pair<>("Number of squadron", Integer.toString(this.getContainers().getSquadrons().size())));
		info.add(new Pair<>("Number of boys", Integer.toString(this.getContainers().members(e -> e.getSex()).size())));
		info.add(
				new Pair<>("Number of girls", Integer.toString(this.getContainers().members(e -> !e.getSex()).size())));
		return info;
	}

	@Override
	public void addMember(final Member m) throws ObjectAlreadyContainedException {
		this.rep.addMembroSenzaSquadriglia(m);
		this.excursions.forEach(e -> {
			if (!(e instanceof UscitaSquadrigliaImpl)) {
				try {
					e.addPartecipant(m, false);
				} catch (Exception e1) {
					new WarningNotice("Errore aggiunta in evento: " + e.getName());
				}
			}
		});
	}

	@Override
	public void createSq(final Squadron sq) throws ObjectAlreadyContainedException {
		this.rep.addSquadron(sq);
	}
	@Override
	public void removeMemberFromSq(Member m) throws ObjectAlreadyContainedException {
		this.rep.getAllSquadron().stream().filter(e -> e.containMember(m)).forEach(e -> {
			try {
				e.removeMembro(m);
				this.excursions.stream().filter(exc -> exc instanceof UscitaSquadriglia).forEach(exc -> {
					if (((UscitaSquadrigliaImpl) exc).getSquadriglia().equals(e)
							&& ((UscitaSquadrigliaImpl) exc).containMember(m)) {
						try {
							exc.removePartecipant(m);
						} catch (Exception e1) {
						}
					}
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		this.rep.addMembroSenzaSquadriglia(m);

	}

	@Override
	public void removeMember(final Member m) throws ObjectNotContainedException {
		if (this.rep.getMembriSenzaSquadriglia().contains(m)) {
			this.rep.removeMembroSenzaSquadriglia(m);
		} else {
			if (this.rep.getAllMember().contains(m)) {
				for (final Squadron sq : this.rep.getAllSquadron()) {
					if (sq.getMembri().containsKey(m)) {
						sq.removeMembro(m);
					}
				}
			}
		}
		this.excursions.forEach(e -> {
			try {
				if (e.containMember(m)) {
					e.removePartecipant(m);
				}
			} catch (Exception exc) {
			}
		});
	}

	@Override
	public void removeSq(final Squadron sq) throws ObjectNotContainedException, ObjectAlreadyContainedException {
		this.rep.removeSquadron(sq);
		for (final Member member : sq.getMembri().keySet()) {
			this.rep.addMembroSenzaSquadriglia(member);
		}
	}

	@Override
	public void putMemberInSq(final Member m, final Squadron sq, final Roles rl)
			throws MemberSexException, ObjectNotContainedException, ObjectAlreadyContainedException {
		this.rep.spostaMembroInSquadriglia(m, rl, sq);
		this.excursions.forEach(e -> {
			if (e instanceof UscitaSquadrigliaImpl) {
				if (((UscitaSquadrigliaImpl) e).getSquadriglia().equals(sq)) {
					try {
						e.addPartecipant(m, false);
					} catch (Exception exc) {
					}
				}
			}
		});
		
	}

	@Override
	public void changeMemberFromSq(final Member m, final Squadron sqNew, final Roles rl)
			throws ObjectNotContainedException, MemberSexException, ObjectAlreadyContainedException {

		if (this.rep.getMembriSenzaSquadriglia().contains(m)) {
			new WarningNotice("Il ragazzo non appartiene a nessuna squadriglia \n verrà comunque assegnato");
		} else {
			this.rep.removeMemberFromSquadron(m);
			this.excursions.forEach(e -> {
				if (e instanceof UscitaSquadrigliaImpl) {
					if (((UscitaSquadrigliaImpl) e).containMember(m)) {
						try {
							e.removePartecipant(m);
						} catch (Exception exc) {
							new WarningNotice("Errore rimozione dagli eventi della squadriglia");
						}
					}
				}
			});
		}

		this.putMemberInSq(m, sqNew, rl);
	}

	@Override
	public LocalDate getLimitDateToPay() {
		return this.rep.getDateToPay();
	}

	@Override
	public List<Member> getMemberDidntPay() {
		return this.rep.getMembersNotPaid(Year.now().getValue());
	}

	@Override
	public void addExcursion(final Excursion exc) {
		this.excursions.add(exc);
		try {
			ControlMail.sendMailForExcursion(exc);
		} catch (MessagingException e) {
			new WarningNotice("Invio automatico delle email fallito. Controllare la connessione");
		}
	}

	@Override
	public Reparto getReparto() {
		return this.rep;
	}

	@Override
	public void removeExcursion(final String name) {
		final List<Excursion> exc = this.excursions.stream().filter(e -> e.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
		if (Integer.valueOf(exc.size()).equals(0)) {
			new WarningNotice("Nessuna escursione corrisponde al nome: " + name);
			return;
		}
		this.excursions.removeAll(exc);
	}

	@Override
	public void removeExcursion(final Excursion exc) {
		if (this.excursions.contains(exc)) {
			this.excursions.remove(exc);
		} else {
			new WarningNotice("Nessuna escursione corrisponde al nome: " + exc.getName());
		}

	}

	@Override
	public List<Member> getMember(final String name, final String surname) {
		return this.rep.getAllMember().stream()
				.filter(e -> e.getName().equalsIgnoreCase(name) && e.getSurname().equalsIgnoreCase(surname))
				.collect(Collectors.toList());
	}

	@Override
	public LocalDate getLastMailSend() {
		return this.lastMailSend;
	}

	@Override
	public void setLastMailSend(final LocalDate lastMailSend) {
		this.lastMailSend = lastMailSend;
	}

	@Override
	public List<Member> getMemberWithName(final String name) {
		return this.rep.getAllMember().stream().filter(e -> e.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}

	@Override
	public List<Member> getMemberWithSurname(final String Surname) {
		return this.rep.getAllMember().stream().filter(e -> e.getSurname().equalsIgnoreCase(Surname))
				.collect(Collectors.toList());
	}
}
