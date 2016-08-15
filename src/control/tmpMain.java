package control;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import control.exception.MoreLeadersNotPermitException;
import model.*;

public class tmpMain {

	public static void main(String[] args) {
		try{
			Unit fenice = new MasterProjectImpl().loadUnit("fenice_allegra");
			System.out.println("MEMBRI: ");
			fenice.getContainers().getMembers().forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
			System.out.println("MEMBRI NO PAGATO: ");
			fenice.getMemberDidntPay().forEach(e -> System.out.println(e.getName() + " " + e.getSurname()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
