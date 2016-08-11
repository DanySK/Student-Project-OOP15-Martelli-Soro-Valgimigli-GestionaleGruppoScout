package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import control.exception.MemberSexException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class test {
	Capo mem1 = new CapoImpl("riccardo", "soro", LocalDate.of(1995, 3, 16), true, "3459239767");
	Capo mem2 = new CapoImpl("chiara", "morgagni", LocalDate.of(1995, 8, 14), false, "3342338362");
	Capo aiutante=new CapoImpl("giorgia","soro",LocalDate.of(1997, 8, 19),false,"3283183601");
	Member mem3 = new MemberImpl("andrea", "pondini", LocalDate.of(1995, 4, 3), true);
	Reparto reparto;
	Squadron squad1 = new SquadronImpl("orsi bruni", true);

	@Test
	public void testRepartoImpl() {
		try {
			reparto = new RepartoImpl(mem1, mem2, new ArrayList<Capo>(), "reparto");
		} catch (MemberSexException e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertTrue(this.reparto.getStaff().size() == 2);
		try {
			reparto.addMembroSenzaSquadriglia(mem3);
		} catch (ObjectAlreadyContainedException e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertTrue(reparto.getMembriSenzaSquadriglia().size() == 1);
		try {
			reparto.addSquadron(squad1);
		} catch (ObjectAlreadyContainedException e) {
			// TODO Auto-generated catch block
			fail();
		}
		try {
			reparto.spostaMembroInSquadriglia(mem3, Roles.CUCINIERE, squad1);
		} catch (MemberSexException e) {
			// TODO Auto-generated catch block
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertTrue(reparto.getMembriSenzaSquadriglia().size() == 0);
		mem3.setTasse(2000);
		assertTrue(reparto.getMembersNotPaid(2000).size() == 0);
		assertTrue(reparto.getMembersNotPaid(2001).size() == 1 && reparto.getMembersNotPaid(2001).get(0).equals(mem3));
		
		reparto.setDateToPay(LocalDate.of(2000, 3, 15));
		assertTrue(reparto.getDateToPay().equals(LocalDate.of(2000, 3, 15)));
		
		assertTrue (reparto.getName().equals("reparto"));
		
		reparto.setName("cambioNome");
		assertTrue (reparto.getName().equals("cambioNome"));
		
		try {
			reparto.addMembroSenzaSquadriglia(mem3);
		} catch (ObjectAlreadyContainedException e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertTrue (reparto.getMembriSenzaSquadriglia().size()==1);
		try {
			reparto.removeMembroSenzaSquadriglia(mem3);
		} catch (ObjectNotContainedException e) {
			// TODO Auto-generated catch block
		fail();
		}
		assertTrue(reparto.getMembriSenzaSquadriglia().size()==0);
		
		assertTrue (reparto.getAllSquadron().size()==1);
		assertTrue(reparto.getAllMember().size()==1);
		
		try {
			assertTrue (reparto.getSquadronOfMember(mem3).equals(squad1));
		} catch (ObjectNotContainedException e) {
			// TODO Auto-generated catch block
			fail();
		}
		try {
			reparto.removeMemberFromSquadron(mem3);
		} catch (ObjectNotContainedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue (reparto.getAllMember().isEmpty());
		
		assertTrue(reparto.getMembriSenzaSquadriglia().size()==1);
		
		assertTrue(reparto.getCapoM().equals(mem1));
		
		assertTrue(reparto.getCapoF().equals(mem2));
		
		try {
			reparto.removeSquadron(squad1);
		} catch (ObjectNotContainedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(reparto.getAllSquadron().isEmpty());
		
		try {
			reparto.addSquadron(squad1);
		} catch (ObjectAlreadyContainedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(reparto.getAllSquadron().size()==1);
		
		assertTrue(reparto.containedSquadron(squad1));
		
		try {
			reparto.addAiutante(aiutante);
		} catch (ObjectAlreadyContainedException e) {
			fail();
			e.printStackTrace();
		}
		assertTrue (reparto.getStaff().size()==3);

		assertTrue(reparto.isContainedAiutante(aiutante));
		
		try {
			reparto.removeAiutante(aiutante);
		} catch (ObjectNotContainedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		assertTrue(reparto.getStaff().size()==2);
		
	
	}

}
