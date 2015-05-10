package ac.il.javacourse.model;

public class Portfolio {
	
	/* This class is a Portfolio of stocks,the maximum of stocks in portfolio is 5*/
	
	private static final int MAX_PORTFOLIO_SIZE = 5 ;
	
	private String Title;
	private Stock[] stocks ;
	private int portfolioSize;
	
	public Portfolio() { 
		this.Title=new String("");
		this.stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize=0;
	}
	
	public Portfolio(String string) {
		this.Title = string;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
	}
	
	/* C'tors of portfolio */ 
	
	public Portfolio (Portfolio oldPortfolio){
		        this(oldPortfolio.getTitle());
				this.portfolioSize=(oldPortfolio.getPortfolioSize());
		 		
		 		this.portfolioSize = oldPortfolio.getPortfolioSize();
		 		
		 		copyStocksArray(oldPortfolio.getStocks(), this.getStocks());	
	}
		 		
		 		
	private void copyStocksArray(Stock[] oldStocksArray, Stock[] newStocksArray ){
		
		for(int i = 0; i<this.portfolioSize; i++){
			newStocksArray[i]= new Stock (oldStocksArray[i]);
		
		}
	}
	/* method that adds stock to portfolio's array */ 
	
	public void addstock(Stock stock)
	{
		if(portfolioSize<MAX_PORTFOLIO_SIZE && stock!=null)
		{
			stocks[this.portfolioSize]=stock;
			portfolioSize++;	
		}
		else 
			System.out.println("Sorry, portfolio is full or stock is null!");
		
	}
	
	public Stock[] getStocks(Stock stocks[]){
		return stocks;
	}
	
	/* method that removes a stock from portfolio with the same symbol as received */ 
public void removeStock(String stockName){
		
		for(int i = 0; i< MAX_PORTFOLIO_SIZE; i++){
			if((this.stocks[i].getSymbol().equals(stockName) == true && stocks[i] != null)){
				if (portfolioSize != 1){
				stocks[i] = stocks[portfolioSize-1];
				}else  if (portfolioSize == 1){
					stocks[i]=null;
				}
				portfolioSize--;
				System.out.println("Stock was deleted as per request");
				return;
			}
		}
		System.out.println("Stock was not found in this Portfolio");
		return;
	}
	
	public String getHtmlString() 
	{
			
		String ret = new String( "<h1>" + getTitle() + "</h1>" );
				
			for(int i = 0; i < portfolioSize ;i++)
			{
					Stock current = this.stocks[i];
					ret += current.getHtmlDescription() + "<br>";
			}
				
				return ret;
	}
	
	
	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	
	
	

}
