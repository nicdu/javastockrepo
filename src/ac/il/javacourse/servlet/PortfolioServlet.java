package ac.il.javacourse.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ac.il.javacourse.model.Portfolio;
import ac.il.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio = portfolioManager .getPortfolio();
		
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		portfolio1.getHtmlString();
		
		resp.getWriter().println(portfolio1.getHtmlString());
		
	}

}

