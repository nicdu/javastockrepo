package ac.il.javacourse.exception;

import org.algo.exception.PortfolioException;


public class StockNotExistExeption extends PortfolioException{
	
	public StockNotExistExeption(){
		super("Stock Was not found in portfolio");
	}
	
	public StockNotExistExeption(String stock){
		super("Stock " + stock +  " Was not found in portfolio");
	}
	

}