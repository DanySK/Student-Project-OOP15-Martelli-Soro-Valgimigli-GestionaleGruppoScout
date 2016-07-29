package control;

import java.time.Year;

import control.exception.MoreLeadersNotPermitException;
import model.*;

public class tmpMain {

	public static void main(String[] args) {
		//testing 
		
		// 1 creare un nuovo reparto
		
		Member leaderM = projectFactoryimpl.getLeaderM("Marco", "Rossi"); 
		Member leaderF = projectFactoryimpl.getLeaderF("Giorgia", "Verdi"); 
		
		Reparto rp = projectFactoryimpl.getReparto(leaderM, leaderF, "Il falco giallo");
		Unit u = projectFactoryimpl.getUnit(rp);
		
		System.out.println("Creato reparto: " + u.info());
		
		// Popolare il reparto
		
		u.addMember(projectFactoryimpl.getSimpleMember("Lorenzo", "Rossi", Year.of(1995).atMonth(12).atDay(12), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Lorenzo", "Verdi", Year.of(1996).atMonth(10).atDay(11), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Lorenzo", "Blu", Year.of(1997).atMonth(10).atDay(12), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Andrea", "Rossi", Year.of(1996).atMonth(10).atDay(16), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Andrea", "Verdi", Year.of(1997).atMonth(7).atDay(29), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Andrea", "Blu", Year.of(1995).atMonth(7).atDay(30), true));
		u.addMember(projectFactoryimpl.getSimpleMember("Lia", "Rossi", Year.of(1996).atMonth(7).atDay(31), false));
		u.addMember(projectFactoryimpl.getSimpleMember("Lia", "Verdi", Year.of(1997).atMonth(8).atDay(2), false));
		u.addMember(projectFactoryimpl.getSimpleMember("Gio", "Rossi", Year.of(1996).atMonth(9).atDay(11), false));
		u.addMember(projectFactoryimpl.getSimpleMember("Gio", "Blu", Year.of(1993).atMonth(8).atDay(7), false));
		
		u.getContainers().getMembers().forEach(e -> System.out.println("Aggiunto: "+ e.getName() + " " + e.getSurname()));
		
		Squadron aquile = projectFactoryimpl.getSquadron("Aquile", true);
		Squadron falchi = projectFactoryimpl.getSquadron("Falchi", true);
		Squadron lepri = projectFactoryimpl.getSquadron("lepri", false);
		
		try {
			aquile.setCapoSq(u.getContainers().members(e -> e.getName().equals("Lorenzo") && e.getSurname().equals("Rossi")).get(0));
			aquile.setVicecapoSq(u.getContainers().members(e -> e.getName().equals("Lorenzo") && e.getSurname().equals("Verdi")).get(0));
			aquile.addMembro(u.getContainers().members(e -> e.getName().equals("Lorenzo") && e.getSurname().equals("Blu")).get(0), Roles.CICALA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			falchi.setCapoSq(u.getContainers().members(e -> e.getName().equals("Andrea") && e.getSurname().equals("Rossi")).get(0));
			falchi.setVicecapoSq(u.getContainers().members(e -> e.getName().equals("Andrea") && e.getSurname().equals("Verdi")).get(0));
			falchi.addMembro(u.getContainers().members(e -> e.getName().equals("Andrea") && e.getSurname().equals("Blu")).get(0),Roles.MAGAZZINIERE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			lepri.setCapoSq(u.getContainers().members(e -> e.getName().equals("Lia") && e.getSurname().equals("Rossi")).get(0));
			lepri.setVicecapoSq(u.getContainers().members(e -> e.getName().equals("Lia") && e.getSurname().equals("Verdi")).get(0));
			lepri.addMembro(u.getContainers().members(e -> e.getName().equals("Gio") && e.getSurname().equals("Blu")).get(0),Roles.CUCINIERE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Create le squadriglie");
		
		// test 
		
		
	}

}
