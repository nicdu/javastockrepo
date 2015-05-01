package ac.il.javacourse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Stock {
	
	public static final int BUY = 0;
	public static final int SELL = 1;
	public static final int REMOVE = 2;
	public static final int HOLD = 3;
	
	private String symbol;
	private float ask,bid;
	private java.util.Date date;
	private int recommendation;
	private int stockQuantity;
	
	
	public Stock(String symbol,float ask,float bid,Date date){
		this.symbol=symbol;
		this.ask=ask;
		this.bid=bid;
		this.date=date;
	}
	
	
	public int getRecommendation() {
		return recommendation;
	}


	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}


	public int getStockQuantity() {
		return stockQuantity;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription(){
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = df.format(getDate());

		String ret= "<br>Stock symbol: </b>"+getSymbol()+" <b>ask: </b>"+getAsk()+"<b> bid: </b>"+getBid()+
				"<b> date: </b>"+ dateStr;
		return ret;
	
	}
	
	

}
