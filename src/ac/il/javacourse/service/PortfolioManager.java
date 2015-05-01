	package ac.il.javacourse.service;
import ac.il.javacourse.StockDetailsServlet;

import java.util.Calendar;
import java.util.Date;

import ac.il.javacourse.Stock;
import ac.il.javacourse.model.Portfolio;

public class PortfolioManager {
	
	public Portfolio getPortfolio()
	{
		Calendar cal=Calendar.getInstance();
		cal.set(2014, 10, 15);
		Date date1=cal.getTime();
		Date date2=cal.getTime();
		Date date3=cal.getTime();
		
		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("targil 5 portfolio");
		
		Stock stock1=new Stock("PIH",13.1F,12.4F,date1);
		Stock stock2=new Stock("AAL",5.78F,5.5F,date2);		
		Stock stock3=new Stock("CAAS",32.2F,31.5F,date3);
		
		portfolio.addstock(stock1);
		portfolio.addstock(stock2);
		portfolio.addstock(stock3);
		
		return (portfolio);
		
	}

}
