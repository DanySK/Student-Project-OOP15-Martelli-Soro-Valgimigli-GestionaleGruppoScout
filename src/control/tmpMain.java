package control;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import control.exception.MoreLeadersNotPermitException;
import model.*;

public class tmpMain {

	public static void main(String[] args) {
		Capo matteo = new CapoImpl("Matteo", "Mani", LocalDate.of(1995, 11, 12), true, "3454458064");
		Capo lisa = new CapoImpl("Lisa", "Bino",LocalDate.of(1993, 11, 12), false, "3453345454" );
		
		Reparto fenice = projectFactoryImpl.getReparto(matteo, lisa, "Fenice allegra");
		Unit feniceUnit = projectFactoryImpl.getUnit(fenice);
		
		try {
			MasterProject mp = new MasterProjectImpl();
			mp.save(feniceUnit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
