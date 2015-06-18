package ac.il.javacourse.servlet;

import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import ac.il.javacourse.exception.BalanceException;
import ac.il.javacourse.exception.PortfolioFullException;
import ac.il.javacourse.exception.StockAlreadyExistsExeption;
import ac.il.javacourse.exception.StockNotExistExeption;
import ac.il.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class InitServlet extends  javax.servlet.http.HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);	

	}
}
