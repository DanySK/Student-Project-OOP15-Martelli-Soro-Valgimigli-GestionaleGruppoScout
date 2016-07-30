package control;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import control.exception.MoreLeadersNotPermitException;
import model.*;

public class tmpMain {

	public static void main(String[] args) {
		//testing 
		
		// 1 creare un nuovo reparto
		
		Member leaderM = projectFactoryImpl.getLeaderM("Marco", "Rossi"); 
		Member leaderF = projectFactoryImpl.getLeaderF("Giorgia", "Verdi"); 
		
		Reparto rp = projectFactoryImpl.getReparto(leaderM, leaderF, "Il falco giallo");
		Unit u = projectFactoryImpl.getUnit(rp);
		
		//System.out.println("Creato reparto: " + u.info());
		
		// Popolare il reparto
		
		u.addMember(projectFactoryImpl.getSimpleMember("Lorenzo", "Rossi", Year.of(1995).atMonth(12).atDay(12), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Lorenzo", "Verdi", Year.of(1996).atMonth(10).atDay(11), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Lorenzo", "Blu", Year.of(1997).atMonth(10).atDay(12), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Andrea", "Rossi", Year.of(1996).atMonth(10).atDay(16), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Andrea", "Verdi", Year.of(1997).atMonth(7).atDay(29), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Andrea", "Blu", Year.of(1995).atMonth(7).atDay(30), true));
		u.addMember(projectFactoryImpl.getSimpleMember("Lia", "Rossi", Year.of(1996).atMonth(7).atDay(31), false));
		u.addMember(projectFactoryImpl.getSimpleMember("Lia", "Verdi", Year.of(1997).atMonth(8).atDay(2), false));
		u.addMember(projectFactoryImpl.getSimpleMember("Gio", "Rossi", Year.of(1996).atMonth(9).atDay(11), false));
		u.addMember(projectFactoryImpl.getSimpleMember("Gio", "Blu", Year.of(1993).atMonth(8).atDay(7), false));
		
		u.getContainers().getMembers().forEach(e -> System.out.println("Aggiunto: "+ e.getName() + " " + e.getSurname()));
		
		Squadron aquile = projectFactoryImpl.getSquadron("Aquile", true);
		Squadron falchi = projectFactoryImpl.getSquadron("Falchi", true);
		Squadron lepri = projectFactoryImpl.getSquadron("Lepri", false);
		
		u.createSq(aquile);
		u.createSq(lepri);
		u.createSq(falchi);
		
		System.out.println("Create le squadriglie");
		
		u.putMemberInSq(u.getContainers().getMember("Lorenzo", "Verdi"), aquile, Roles.MAGAZZINIERE);
		
		u.putMemberInSq(u.getContainers().getMember("Lorenzo", "Blu"), aquile, Roles.CICALA);
		u.putMemberInSq(u.getContainers().getMember("Lorenzo", "Rossi"), aquile, Roles.CUCINIERE);
		u.putMemberInSq(u.getContainers().getMember("Andrea", "Verdi"), falchi, Roles.MAGAZZINIERE);
		u.putMemberInSq(u.getContainers().getMember("Andrea", "Blu"), falchi, Roles.CICALA);
		u.putMemberInSq(u.getContainers().getMember("Andrea", "Rossi"), falchi, Roles.CUCINIERE);

		System.out.println(aquile.getMembri().keySet());
		System.out.println(u.getContainers().getFreeMember().size());
		System.out.println(u.getContainers().getMembers().size());
		System.out.println(u.getContainers().getSquadrons().stream().mapToInt(e -> e.getMembri().size()).sum());
		
		u.getContainers().getSquadrons().forEach(e -> System.out.println("Aggiunta: " + e.getNome()));
		
		// test 
		
		InfoProject info = new InfoProjectImpl();
		System.out.println("-----------------------------------");
		System.out.println(info.getSquadronGeneralInfo("Aquile", u.getContainers()));
		System.out.println("-----------------------------------");
		System.out.println(info.getSquadronGeneralInfo("Falchi", u.getContainers()));

		System.out.println(u.getContainers().getMembers().size());
		
		// test container
		System.out.println("RICERCA LORENZO");
		u.getContainers().findMember("Lorenzo").forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		System.out.println("RICERCA ANDREA");
		u.getContainers().members(e -> e.getName().equals("Andrea")).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		System.out.println("RICERCA TUTTI I 95");
		u.getContainers().members(e -> e.getAnnata() == 21).forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		
		// test excursion 
		try{
			Uscita us = projectFactoryImpl.getStdExcursion(LocalDate.of(2016, 10, 1), u.getReparto(), "Che bella");
			UscitaSquadriglia usq = projectFactoryImpl.getSqExcursion(LocalDate.of(2016, 10, 1), 2, u.getContainers().getSquadrons().get(0) ,"WooW");
			
			us.addPartecipante(u.getContainers().getMember("Lorenzo", "Blu"), true);
			u.addExcursion(us);
			u.addExcursion(usq);
			
			u.getContainers().getExcursion().forEach(e -> System.out.println("Name: " + 
							e.getName() + "\n" + "Partecipanti" + e.getAllPartecipanti() + 
							"\n Non pagato:" + e.getNonPaganti()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
