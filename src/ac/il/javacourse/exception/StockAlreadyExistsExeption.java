package ac.il.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsExeption extends PortfolioException{
	
	public StockAlreadyExistsExeption(){
		super("Stock already exists in portfolio.");
	}
	public StockAlreadyExistsExeption(String symbol){
		super("Stock "+symbol+" already exists in portfolio.");
	}
}