package control;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import model.escursioni.Uscita;
import model.reparto.Capo;
import model.reparto.Reparto;

public class testTest {

	@Test
	public void test() {
		// creazione reparto
		try {
			Capo leaderM = ProjectFactoryImpl.getLeaderM("Marco", "Mitraglia", LocalDate.of(1993, 11, 9), "3454565678");
			Capo leaderF = ProjectFactoryImpl.getLeaderF("Marcella", "Rossi", LocalDate.of(1993, 11, 9), "3985657890");
			Reparto rp = ProjectFactoryImpl.getReparto(leaderM, leaderF, "Fenice viola");
			
			assertTrue(rp.getCapoM().equals(leaderM));
			assertTrue(rp.getCapoF().equals(leaderF));
			
			Unit unit = ProjectFactoryImpl.getUnit(rp);
			
			assertTrue(unit.getReparto().equals(rp));
			assertTrue(unit.getReparto().getName().equals("Fenice viola"));
		
		
			MasterProject mp = new MasterProjectImpl();
			mp.save(unit);
			Unit fenice = mp.loadUnit(unit.getName());
			assertTrue(fenice.getName().equals(unit.getName()));
			assertTrue(fenice.getReparto().getCapoF().getName().equals(unit.getReparto().getCapoF().getName()));
			assertTrue(fenice.getContainers().getMembers().size() == unit.getContainers().getMembers().size());
			//caricamento avvenuto con sucesso
			
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Andrea", "Rossi", LocalDate.of(2002, 12, 11), true));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Lollo", "Verdi", LocalDate.of(2002, 8, 18), true));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Riki", "Blu", LocalDate.of(2002, 8, 22), true));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Mario", "Rasi", LocalDate.of(2003, 12, 11), true));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Anna", "Proti", LocalDate.of(2002, 12, 11), false));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Gio", "Prati", LocalDate.of(2002, 8, 17), false));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Selly", "Scroto", LocalDate.of(2003, 12, 11), false));
			fenice.addMember(ProjectFactoryImpl.getSimpleMember("Maria", "Sano", LocalDate.of(2001, 12, 11), false));
			
			assertTrue(fenice.getContainers().getMembers().size() == 8);
			assertTrue(fenice.getContainers().getFreeMember().size() == 8);
			Container ct = fenice.getContainers(); 
			assertTrue(ct.getMember("Andrea", "Rossi").getName().equals("Andrea"));
			
			
			fenice.getMember("Lollo", "Verdi").setTutorMail("lorenzo.valgimigli@libero.it");

			assertTrue(new SortMemberImpl().sortByAge(ct.getMembers()).get(0).getName().equals("Maria"));
			Uscita usc = ProjectFactoryImpl.getStdExcursion(LocalDate.now().plus(5, ChronoUnit.DAYS), fenice.getReparto() , "Yuuuppi");
			assertTrue(usc.getNotPaied().size() == 8);
			
			usc.setPlace("Camaldoli");
			fenice.addExcursion(usc);
			Checker ch = new CheckerImpl();
			ch.stdRouting(fenice).entrySet().forEach(e -> System.out.println(e.getKey() +" " +e.getValue()));
			ch.stdRouting(fenice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
