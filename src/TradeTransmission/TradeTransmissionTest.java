package TradeTransmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import tradeTransmissionWithoutGradle.Trade;


public class TradeTransmissionTest {
	
	TradeFlow tf = new TradeFlow();
	Date todaysDate=Calendar.getInstance ().getTime ();
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	void testIfTradeEmpty()
	{
		assertTrue(tf.isTradeEmpty());
	}
	
	@Test
	void testAddTrade() throws Exception
	{
		Date maturityDate = sd.parse("20/05/2021");
		Trade t1=new Trade("T1",1,"CP-1","B1",maturityDate, todaysDate, "N");
        tf.addTrades(t1);
       
        assertEquals(0, tf.allTrades.size());   
	}
	
	@Test
	void testVersionAndCpId() throws Exception
	{
		Date maturityDate = sd.parse("30/06/2023");
		Trade t2 = new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, "N");
		tf.addTrades(t2);
		
		Trade t3=new Trade("T2",3,"CP-4","B1",maturityDate, todaysDate, "N");
        tf.addTrades(t3);
        
        assertEquals("CP-4", tf.allTrades.get("T2").getCounterPartyId());
        assertEquals(3, tf.allTrades.get("T2").getVersion());
	}
	
	@Test 
	void testSameVersion() throws Exception
	{
		Date maturityDate = sd.parse("10/03/2023");
		Trade t4 = new Trade("T1",1,"CP-2","B1",maturityDate, todaysDate, "N");
		tf.addTrades(t4);
		
		assertEquals("CP-2", tf.allTrades.get("T1").getCounterPartyId());
	}
	
	@Test
	void testLowerVersion() throws Exception
	{
		Date maturityDate = sd.parse("10/07/2025");
		Trade t5 = new Trade("T3",4,"CP-3","B1",maturityDate, todaysDate, "N");
		tf.addTrades(t5);
		
		Trade t6 = new Trade("T3",2,"CP-3","B1",maturityDate, todaysDate, "N");
		assertThrows(Exception.class, ()-> tf.addTrades(t6), "2 is less than 4");
	}
	
	@Test
	void checkGreaterMaturityDate() throws Exception
	{
		Date maturityDate = sd.parse("10/07/2024");
		Trade t7 = new Trade("T4",2,"CP-3","B1",maturityDate, todaysDate, "N");
		tf.addTrades(t7);
		
		assertEquals(t7, tf.allTrades.get("T4"));
		
	}
	
	@Test
	void checkLowerMaturity() throws Exception
	{
		Date maturityDate = sd.parse("10/07/2020");
		Trade t8 = new Trade("T5",2,"CP-3","B1",maturityDate, todaysDate, "N");
		tf.addTrades(t8);
		
		assertNull(tf.allTrades.get("T5"));
	}
	
	@Test
	void testMaturityLowerVersionSame() throws Exception
	{
		Date maturityDate1 = sd.parse("05/12/2023");
		Trade t9 = new Trade("T6",2,"CP-3","B1",maturityDate1, todaysDate, "N");
		tf.addTrades(t9);
		
		Date maturityDate2 = sd.parse("05/12/2020");
		Trade t10 = new Trade("T6",2,"CP-2","B1",maturityDate2, todaysDate, "N");
        tf.addTrades(t10);
        assertEquals(maturityDate1,tf.allTrades.get("T6").getMaturityDate());
	}
	
	@Test
	void testSameMaturityDateAndTodaysDate() throws Exception
	{
		Trade t11 = new Trade("T7",7,"CP-5","B4",todaysDate, todaysDate, "N");
		tf.addTrades(t11);
		
		assertNotNull(tf.allTrades.get("T7"));
	}
	
	@Test
	void testHigherVersionLowerMaturity() throws Exception
	{
		Date maturityDate1 = sd.parse("25/08/2022");
		Trade t12 = new Trade("T8", 3, "CP-2", "B1", maturityDate1, todaysDate, "N");
		tf.addTrades(t12);
		
		Date maturityDate = sd.parse("25/08/2021");
		Trade t13 = new Trade("T8", 5, "CP-2", "B1", maturityDate, todaysDate, "N");
		tf.addTrades(t13);
		
		assertEquals(3, tf.allTrades.get("T8").getVersion());
		assertEquals(maturityDate1, tf.allTrades.get("T8").getMaturityDate());
	}
	
	@Test
	void testLowerVersionAndLowerMaturityDate() throws Exception
	{
		Date maturityDate = sd.parse("09/06/2023");
		Trade t14 = new Trade("T9", 5, "CP-2", "B1", maturityDate, todaysDate, "N");
		tf.addTrades(t14);
		
		maturityDate = sd.parse("09/06/2020");
		Trade t15 = new Trade("T9", 3, "CP-2", "B1", maturityDate, todaysDate, "N");
		
		assertThrows(Exception.class, ()->tf.addTrades(t15), "3 is less than 5");
	}
	
	@Test
	void testExpiryDate() throws ParseException
	{
		Date maturityDate = sd.parse("23/09/2018");
		Trade t16 = new Trade("T10", 3, "CP-2", "B1", maturityDate, todaysDate, "N");
		tf.allTrades.put("T10",t16);
		tf.checkExpiredTrade();
		assertEquals("Y", tf.allTrades.get("T10").getExpired());
	}
	
	

}
