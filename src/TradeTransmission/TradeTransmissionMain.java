package TradeTransmission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TradeTransmissionMain {
	
	public static void main(String args[]) throws Exception
		{
			TradeFlow tf = new TradeFlow();
			Date todaysDate = Calendar.getInstance ().getTime ();
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			
			Date maturityDate = sd.parse("10/05/2022");
			Trade t1 = new Trade("T1", 1, "CP-1", "B1", maturityDate, todaysDate, "N");
			tf.addTrades(t1);
			
			maturityDate = sd.parse("20/05/2021");
			Trade t2 = new Trade("T2", 2, "CP-2", "B1", maturityDate, todaysDate, "N");
			tf.addTrades(t2);
			
			maturityDate = sd.parse("10/05/2022");
			Trade t4 = new Trade("T2", 5, "CP-4", "B1", maturityDate, todaysDate, "N");
			tf.addTrades(t4);
			
			maturityDate = sd.parse("20/05/2023");
			Trade t3 = new Trade("T4", 5, "CP-3", "B2", maturityDate, todaysDate, "N");
			tf.addTrades(t3);
			
			maturityDate = sd.parse("20/05/2023");
			Trade t5 = new Trade("T4", 6, "CP-3", "B2", maturityDate, todaysDate, "N");
			tf.addTrades(t5);
			//System.out.println(t5);
			
			System.out.println("\n\n");
			System.out.println("Displaying total number of Trades in the list");
			tf.printAllTrades();
			System.out.println("\n\n");	
			
			System.out.println("Checking for expired trades");
			maturityDate = sd.parse("20/05/2020");
			Trade t6 = new Trade("T2", 2, "CP-2", "B1", maturityDate, todaysDate, "N");
			tf.allTrades.replace("T2", t6);
			
			maturityDate = sd.parse("20/05/2020");
			Trade t7 = new Trade("T4", 5, "CP-3", "B2", maturityDate, todaysDate, "N");
			tf.allTrades.replace("T4", t7);
			tf.checkExpiredTrade();
			tf.printAllTrades();
		}

}
