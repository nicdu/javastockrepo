package ac.il.javacourse.model;

import ac.il.javacourse.Stock;

public class Portfolio {
	
	private static final int MAX_PORTFOLIO_SIZE = 5 ;
	
	private String Title;
	private Stock[] stocks ;
	private int portfolioSize;
	
	public Portfolio() { 
		this.Title=new String("");
		this.stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize=0;
	}
	
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
