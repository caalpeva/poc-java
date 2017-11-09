package team.boobee.samples.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(request, response);
	}
	
    /**************************************************************************/
    /*************************** METODOS PRIVADOS *****************************/
    /**************************************************************************/
	
	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Served at: " + request.getContextPath());
		System.out.println("A request arrived for " + request.getServletPath());
		
		request.setAttribute("firstName", "John");
		request.setAttribute("lastName", "Doe");
		
		//String forwardStr = "/result.jsp?username=foo&password=bar";
		String forwardStr = "/getRequestData";
		System.out.println("Forwarding to " + forwardStr);
		request.getRequestDispatcher(forwardStr).forward(request, response);
	}
}