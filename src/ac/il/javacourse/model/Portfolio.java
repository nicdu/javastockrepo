package ac.il.javacourse.model;

public class Portfolio {
	
	/* This class is a Portfolio of stocks,the maximum of stocks in portfolio is 5*/
	
	private static final int MAX_PORTFOLIO_SIZE = 5 ;
	
	public enum ALGO_RECOMMENDATION { 
		BUY,SELL,REMOVE,HOLD
	}
	
	private String Title;
	private Stock[] stocks ;
	private int portfolioSize;
	private float balance;
	
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
	
/***********************************************************************************/	
	/* C'tors of portfolio */ 
	
	public Portfolio (Portfolio oldPortfolio){
		        this(oldPortfolio.getTitle());
				this.portfolioSize=(oldPortfolio.getPortfolioSize());
		 		
		 		this.portfolioSize = oldPortfolio.getPortfolioSize();
		 		
		 		copyStocksArray(oldPortfolio.getStocks(), this.getStocks());	
	}
		 		
/*************************************************************************************/		 		
	private void copyStocksArray(Stock[] oldStocksArray, Stock[] newStocksArray ){
		
		for(int i = 0; i<this.portfolioSize; i++){
			newStocksArray[i]= new Stock (oldStocksArray[i]);
		
		}
	}
/*********************************************************************************************/	
	/* method that update balance , balance is the amount of money available in portfolio for investment*/
	public void updateBalance(float amount){
		float temp=this.balance+amount;
		if (temp<0)
			System.out.println("the balance is negative don't update" );
		else{
			this.balance=temp;
			System.out.println("the balance was updated ");
		}
	}
/******************************************************************************/	
	
	/* method that adds stock to portfolio's array */ 
	
	public void addstock(Stock stock)
	{
		if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only "+this.portfolioSize+" stocks”");
			return;
	}
	else if (stock == null){
		System.out.println("stock is null check stock");
		return;
	}
		
	else {
		int i = this.findstock (stock.getSymbol());
		if(i != -1){
			System.out.println("Stock already exists in portfolio.");
			return;
		}
	}

    stocks[this.portfolioSize] = stock;
    stocks[this.portfolioSize].setStockQuantity(0); 
    this.portfolioSize++;
    return;
}
	/* method that finds stock in in array*/
	public int findstock(String stock){
		for(int i=0;i<this.portfolioSize;i++)
			if (stock.equals(this.stocks[i].getSymbol()))
				return i;
		return -1;
		
	}
/************************************************************************************/
	public Stock[] getStocks(Stock stocks[]){
		return stocks;
	}
	
/***********************************************************************************/
	
	/* method that removes a stock from portfolio with the same symbol as received */ 
   public boolean removeStock(String stockName){
	   if (stockName==null){
		   System.out.println("the stockName in invalid");
		   return false;
	   }
       int place=findstock(stockName);
       if (place>-1){
    	   if(this.portfolioSize>1){
    		   this.sellStock(stocks[place].getSymbol(),-1);
    		   stocks[place]=stocks[this.portfolioSize-1];
    		   stocks[this.portfolioSize-1]=null;
    	   }else if (this.portfolioSize==1){
    		   this.sellStock(stocks[place].getSymbol(),-1);
    		   stocks[place]=null;
    	   }
    	   portfolioSize--;
    	   System.out.println("stock was deleted as requested");
    	   return true;
       }
       System.out.println("stock wasn't found");
       return false;
   }
   
 /*************************************************************************************/
   /* method that sells stocks*/
   
   public boolean sellStock(String symbol,int quantity){
	   if(symbol == null || quantity < -1){
			System.out.println("There is an error!");
			return false;
		}
	   int i=this.findstock(symbol);
	   if(i>-1){
		   if(this.stocks[i].getStockQuantity()-quantity<0){
			   System.out.println("not enough stocks to sell");
			   return false;
		   }else if (quantity==-1){
			   this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
			   this.stocks[i].setStockQuantity(0);
			   System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
			   return true;

			}else {
				this.updateBalance(quantity*this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
				System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
				return true;
			}
		}
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}
/*************************************************************************************/
   /* method that buys stocks*/
   
   public boolean buyStock(Stock stock,int quantity){
	   if(stock == null || quantity < -1){
			System.out.println("There is an error!");
			return false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to purchase the stock.");
			return false;
		}
	    /* if the stock is already exist in array */ 
		int i = this.findstock (stock.getSymbol());
			
		if(i>-1){
			if(quantity == -1){
				int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-howManyToBuy*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
				System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
						+ "was bought succefully.");
				return true;

			}else {
				this.updateBalance(-quantity*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
				System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
				return true;
			}
		}

		
		
		/* is the stock does not exist in the array */
		if(i == MAX_PORTFOLIO_SIZE-1){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}

		if (quantity == -1){
			this.addstock(stock); //add the stock to portfolioSize-1 in the stocks array.
			int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(howManyToBuy*this.stocks[this.portfolioSize-1].getAsk()));
			this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity()+howManyToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return true;
		} else {
			this.addstock(stock); //add the stock to portfolioSize-1 in the stocks array.
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			this.stocks[this.portfolioSize -1].setStockQuantity(quantity);
			System.out.println("Stock "+stock.getSymbol()+" was added successfuly to the portfolio. With quantity of "
					+ quantity+" stocks.");
			return true;

		}
	}
 /************************************************************************************/
   /*method that returns total value of all stocks*/
   public float getStocksValue(){
	   float totalvalue=0;
	   for(int i=0;i<this.portfolioSize;i++)
		   totalvalue+=this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
	   return(totalvalue);
   }
/*************************************************************************************/
   public float getTotalValue(){
	   return(this.getBalance()+this.getStocksValue());
   }
/*************************************************************************************/
   
	public String getHtmlString() 
	{
			
		String ret = new String( "<h1>" + getTitle() + "</h1>" );
				
			for(int i = 0; i < portfolioSize ;i++)
			{
					Stock current = this.stocks[i];
					if (current != null){
						ret = ret + current.getHtmlDescription()+"<br>";
					}
			}
			ret += ("Total Portfolio Value :"+this.getTotalValue()+ "$, "+
							"Total Stocks Value :"+this.getStocksValue()+"$, "+"Balance :"+this.balance+"$");
				
				return ret;
	}
	
/****************************************************************************/
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
	 /* method that returns value of balance*/
	   
	   public float getBalance(){
		   return balance;
	   }
	
}
