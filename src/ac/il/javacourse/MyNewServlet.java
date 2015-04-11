package ac.il.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyNewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("Hello my new Servlet");
		
		double Radius,Area,Two;
		Radius=50;
		Two=2;
		Area=Math.PI*Math.pow(Radius, Two);
		String AreaStr= new String( "<h1>calculation 1 : area of circle with radius "+Radius+" is : "+Area+"</h1>"); 
		
		double AngleB,Hypotenuse,Opposite;
		AngleB=30;
		Hypotenuse=50;
		Opposite=Hypotenuse*Math.sin(Math.toRadians(AngleB));
		String OppositeStr= new String( "<h1>calculation 2 : length of opposite where angle B is : "+AngleB+" degrees and hypotenuse length is "+Hypotenuse+" cm is  "+Opposite+"</h1>"); 
		
		double Power,Base,Exp;
		Base=20;
		Exp=13;
		Power=Math.pow(Base, Exp);
		String PowerStr=new String( "<h1> calculation 3 : power of "+Base+" wiht exp of "+Exp+" is : "+Power+"</h1>");
		
		String ResultStr=new String("<h1> "+AreaStr+"<br>"+OppositeStr+"<br>"+PowerStr+"</h1>");
		resp.getWriter().println(ResultStr);
		
	}

}
