package TradeTransmission;
import java.util.Date;
import java.util.HashMap;


public class TradeFlow {
	
	HashMap<String, Trade> allTrades = new HashMap<String, Trade>();
	
	public boolean isTradeEmpty()
	{
		return allTrades.isEmpty();
	}
	
	public void checkVersion(Trade t, int version) throws Exception
	{
		if(t.getVersion() < version)
		{
			throw new Exception (t.getVersion()+" is less than "+version);
		}
	}
	
	public Boolean checkMaturityDate(Date maturityDate, Date CurrentDate)
	{
		if(CurrentDate.compareTo(maturityDate) > 0)
			return false;
		else
			return true;
	}
	
	public void checkExpiredTrade()
	{
		Date currentDate = new Date();
		
		for(String strKey : allTrades.keySet())
		{
			if(currentDate.compareTo(allTrades.get(strKey).getMaturityDate()) > 0)
			{
				Trade t = allTrades.get(strKey);
				t.setExpired("Y");
				allTrades.replace(strKey, t);
			}
		}
	}
	
	public void addTrades(Trade T) throws Exception
	{
		if(allTrades.containsKey(T.getTradeId()))
		{
			checkVersion(T, allTrades.get(T.getTradeId()).getVersion());
			if(checkMaturityDate(T.getMaturityDate(), allTrades.get(T.getTradeId()).getMaturityDate()))
			{
				allTrades.replace(T.getTradeId(), T);
				System.out.println(T.getTradeId()+ " is added to the store");
			}
			else
			{
				System.out.println("Not able to add "+T.getTradeId()+" in the store as maturity date is lower than current date");
			}
		}
		else
		{
			if(checkMaturityDate(T.getMaturityDate(), T.getCreatedDate()))
			{
					allTrades.put(T.getTradeId(), T);
					System.out.println(T.getTradeId()+" is added to the Store");
			}
			else
			{
				System.out.println("Not able to add "+T.getTradeId()+" in the store as maturity date is lower than current date");
			}
		}
		
	}
	
	
	public Trade getTrade(String tId) throws Exception
	{
		if(allTrades.containsKey(tId))
			return allTrades.get(tId);
		else
			throw new Exception ("Trade with"+ tId +"Not found");
	}
	
	
	public void printAllTrades()
	{
		for(String tid : allTrades.keySet())
		{
			System.out.println(allTrades.get(tid).toString());
		}
	}

}
