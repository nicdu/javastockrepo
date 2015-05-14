package ac.il.javacourse.service;

import java.util.Date;
import java.util.Calendar;

import ac.il.javacourse.model.Portfolio;
import ac.il.javacourse.model.Stock;

public class PortfolioManager {
	
	/* This class is a portfolio manager , it will execute methods of portfolio*/
	
	public Portfolio getPortfolio()
	{
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 portfolio");	
		myPortfolio.updateBalance(10000);
		
		
		Calendar cal=Calendar.getInstance();
		cal.set(2014, 11, 15);
		Date date1=(Date) cal.getTime();
		Date date2=(Date) cal.getTime();
		Date date3=(Date) cal.getTime();
		
		Stock stock1=new Stock("PIH",10F,8.5F,date1);
		Stock stock2=new Stock("AAL",30F,25.5F,date2);		
		Stock stock3=new Stock("CAAS",20F,15.5F,date3);
		
		myPortfolio.buyStock(stock1, 20);
		myPortfolio.buyStock(stock2, 30);
		myPortfolio.buyStock(stock3, 40);

		myPortfolio.sellStock("AAL",-1);
		myPortfolio.removeStock("CAAS");
		
		return (myPortfolio);
		
	}

}
