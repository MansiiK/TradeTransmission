package TradeTransmission;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trade {
	
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private Date maturityDate;
	private Date createdDate;
	private String expired;
	
	public Trade(String tid, int ver, String cpid, String bid, Date mdate, Date cdate, String exp)
	{
		tradeId = tid;
		version = ver;
		counterPartyId = cpid;
		bookId = bid;
		maturityDate = mdate;
		createdDate = cdate;
		expired = exp;
	}

	// getter and setter methods
	
	
	public String getTradeId() 
	{
		return tradeId;
	}

	public void setTradeId(String TradeId) 
	{
		tradeId = TradeId;
	}

	public int getVersion() 
	{
		return version;
	}

	public void setVersion(int Version) 
	{
		version = Version;
	}

	public String getCounterPartyId() 
	{
		return counterPartyId;
	}

	public void setCounterPartyId(String CounterPartyId) 
	{
		counterPartyId = CounterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String BookId) 
	{
		bookId = BookId;
	}

	public Date getMaturityDate() 
	{
		return maturityDate;
	}

	public void setMaturityDate(Date MaturityDate)
	{
		maturityDate = MaturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date CreatedDate) 
	{
		createdDate = CreatedDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String Expired) 
	{
		expired = Expired;
	}
	
	
	public String toString()
	{
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		return ""+tradeId+" "+version+" "+counterPartyId+" "+bookId+" "+sdFormat.format(maturityDate)+" "+sdFormat.format(createdDate)+" "+expired+"";
	}
	
	

}
