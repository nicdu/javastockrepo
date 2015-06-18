package ac.il.javacourse.model;

import java.text.DecimalFormat;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import ac.il.javacourse.*;
import ac.il.javacourse.exception.BalanceException;
import ac.il.javacourse.exception.PortfolioFullException;
import ac.il.javacourse.exception.StockAlreadyExistsExeption;
import ac.il.javacourse.exception.StockNotExistExeption;
import ac.il.javacourse.service.PortfolioManager;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 */
public class Portfolio implements PortfolioInterface{

	private static final int MAX_PORTFOLIO_SIZE = 5;

	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}

	private String title;
	private StockInterface[] stocks;
	private int portfolioSize;
	private float balance;


	/**
	 * C'tor of Portfolio.
	 */
	public Portfolio() {
		this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}

	/**
	 * C'tor of Portfolio.
	 */
	public Portfolio(StockInterface[] stocksArray) {
		this();
		this.portfolioSize = stocksArray.length;
		this.copyStocksArray(stocksArray, stocks);
	}


	/**
	 * Copy C'tor of Portfolio.
	 */

	public Portfolio(String string) {
		this.title = string;
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}

	/**
	 * copy C'tor for Portfolio type.
	 */
	public Portfolio (Portfolio oldPortfolio){

		this(oldPortfolio.getTitle());
		this.portfolioSize = oldPortfolio.getPortfolioSize();
		this.setBalance(oldPortfolio.getBalance());
		copyStocksArray(oldPortfolio.getStocks(), this.getStocks());	
	}


	/**
	 * copy an array of stock from one array (old) to new empty array (new).
	 */

	private void copyStocksArray(StockInterface[] oldStockInterfaces, StockInterface[] newStockInterfaces ){

		for(int i = 0; i<this.portfolioSize; i++){
			newStockInterfaces[i]= new Stock ((Stock)oldStockInterfaces[i]);

		}
	}

	/**
	 * Add Stock to the portfolio's array of stocks.
	 */

	public void addStock(Stock stock) throws StockAlreadyExistsExeption, PortfolioFullException{

		if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}else if (stock == null){
			System.out.println("There is an error with stock received! (Check if it it istanciated)");

		}else {
			int i = this.findStockPlace (stock.getSymbol());
			if(i != -1){
				throw new StockAlreadyExistsExeption(stock.getSymbol());
			}
		}

		stocks[this.portfolioSize] = stock;
		((Stock) stocks[this.portfolioSize]).setStockQuantity(0); 
		this.portfolioSize++;
		return;
	}




	/**
	 * Removes all stocks from portfolio with the same symbol as received. 
	 * @param stockSymbol : the stock's symbol
	 */
	public void removeStock(String stockName) throws StockNotExistExeption, BalanceException{
		int i =0;
		if (stockName == null){
			throw new StockNotExistExeption("The stock received is invalid!");
		}

		i = this.findStockPlace (stockName);

		if(i>-1){
			if (portfolioSize > 1){
				try {
					this.sellStock(stocks[i].getSymbol(), -1);
				} catch (StockNotExistExeption e) {
					e.getMessage();
					e.printStackTrace();
					throw e;
				} 
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;

			}else  if (this.portfolioSize == 1){
				try {
					this.sellStock(stocks[i].getSymbol(), -1);
				} catch (StockNotExistExeption e) {
					e.getMessage();
					e.printStackTrace();
					throw e;
				}
				stocks[i]=null;
			}
			portfolioSize--;
			System.out.println("Stock "+stockName+" was deleted as per request");
		}
		else{
			throw new StockNotExistExeption();
		}
	}

	/**
	 * Method return true if the stock recommendation was updated to SELL otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire stock quantity 
	 * will be sold
	 */

	public void sellStock(String symbol, int quantity) throws IllegalArgumentException, StockNotExistExeption,BalanceException{

		if(symbol == null || quantity < -1){
			throw new IllegalArgumentException("There is an error! Please check your stock symbol or stock quntity.");
		}

		int i = this.findStockPlace (symbol);

		if(i>-1){	
			if(((Stock) this.stocks[i]).getStockQuantity() - quantity < 0){
				throw new IllegalArgumentException("Not enough stocks to sell");
			}else if(quantity == -1){
				this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(0);
				System.out.println("Entire stock ("+symbol+") holdings was sold succefully");
				return ;

			}else {
				this.updateBalance(quantity*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()-quantity);
				System.out.println("An amount of "+quantity+" of stock ("+symbol+") was sold succefully");
				return ;
			}
		}
		throw new StockNotExistExeption("Stock was not found in this Portfolio");

	}

	/**
	 * Method return true if the stock recommendation was updated to BUY otherwise return false. an error will be shown 
	 * on screen in case of one.
	 * Method will update the stock quantity as per request. In case quantity will be "-1" the entire balance quantity 
	 * will be used
	 */

	public void buyStock(Stock stock, int quantity) throws IllegalArgumentException, PortfolioFullException,BalanceException, StockAlreadyExistsExeption, StockNotExistExeption{
		
		if(stock == null || quantity < -1){
			throw new IllegalArgumentException("There is an error! Please check your stock symbol or stock quntity.");
		}

		int stockLocation = 0;
		stockLocation = this.findStockPlace (stock.getSymbol());

		if(quantity*stock.getAsk() > this.balance){
			throw new BalanceException();
		}

		if(stockLocation == MAX_PORTFOLIO_SIZE-1){
			throw new PortfolioFullException();
		}


		if(stockLocation == -1){ 	 		
			try {								
				this.addStock(stock);

			} catch (StockAlreadyExistsExeption e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}				

		}

		if(quantity == -1){
			stockLocation = this.findStockPlace (stock.getSymbol());

			int howManyToBuy = (int)this.balance/(int)this.stocks[stockLocation].getAsk();
			try {
				this.updateBalance(-howManyToBuy*this.stocks[stockLocation].getAsk());
			} catch (BalanceException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) this.stocks[stockLocation]).getStockQuantity()+howManyToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return;

		}else {
			stockLocation = this.findStockPlace (stock.getSymbol());
			try {
				this.updateBalance(-quantity*this.stocks[stockLocation].getAsk());
			} catch (BalanceException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}
			((Stock) this.stocks[stockLocation]).setStockQuantity(((Stock) stocks[stockLocation]).getStockQuantity()+quantity);
			System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
			return;
		}
	}


	/**
	 * Method calculates the portfolio's total stocks value.
	 * @return float representing portfolio's total stocks value.
	 */
	public float getStocksValue(){
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += ((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid();
		}
		return totalValue;		
	}

	/**
	 * Method calculates the portfolio's total value.
	 * @return float representing portfolio's total value.
	 */
	public float getTotalValue(){

		return this.getStocksValue()+this.balance;		
	}


	/**
	 * Method uses the portfolio's stock details.
	 */
	public String getHtmlString(){
		DecimalFormat decimalFormat=new DecimalFormat("#.#"); // SHOULD WE USE IT OR NOT?
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";

		for(int i=0; i<portfolioSize; i++)
		{
			Stock tempStock = (Stock) stocks[i];
			if (tempStock != null){
				htmlResString = htmlResString + tempStock.getHtmlDescription()+"<br>";
			}
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$, "+
				"Total Stocks Value :"+this.getStocksValue()+"$, "+"Balance :"+this.balance+"$.";
		return htmlResString;	
	}


	/**
	 * method receives amount and calculates the current balance.
	 */
	public void updateBalance (float amount) throws BalanceException{
		float tempBalance = this.balance + amount;
		if(tempBalance < 0){
			throw new BalanceException("Please note you may not change balance to negative amount!");
		}else {
			this.balance = tempBalance;
			System.out.println("Balance has been updated to "+ this.balance);
		}

	}

	/**
	 * Find the place of a stock in stocks array.
	 */
	private int findStockPlace (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find the place of a stock in stocks array.
	 */
	public StockInterface findStock (String stockToFind) throws StockNotExistExeption{
		int i = 0;
		for( i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return this.stocks[i];
			}
		}
		throw new StockNotExistExeption();
	}

	/**
	 * return the logical portfolio size
	 */
	private int getPortfolioSizeMethod(StockInterface[] array){
		int i=0;
		for (i=0; i< array.length ; i++){
			if (array[i] == null){
				return i;
			}
		}
		return i;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public StockInterface[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public float getBalance() {
		return balance;
	}
	private void setBalance(float balance) {
		this.balance = balance;
	}
}