package DataETL;

import java.util.Calendar;
import java.util.Map.Entry;

public class App {
	

	public static void main(String[] args) {
		EnergyInfoETL etl=new EnergyInfoETL();
		Calendar cal=Calendar.getInstance();
		for (int i = 1; i < 31; i++) {
			cal.set(2014, 7,i);;
			etl.air(cal);
			etl.water(cal);
			etl.gas(cal);
			etl.elec(cal);
			etl.store();
			System.out.println(i);
		}
	}
}


