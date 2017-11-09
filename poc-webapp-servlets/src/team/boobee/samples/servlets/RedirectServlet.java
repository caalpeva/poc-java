package team.boobee.samples.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		redirect(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		redirect(request, response);
	}
	
    /**************************************************************************/
    /*************************** METODOS PRIVADOS *****************************/
    /**************************************************************************/
	
	private void redirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("A request arrived for " + request.getServletPath());
		
		request.setAttribute("firstName", "John");
		request.setAttribute("lastName", "Doe");
		
		//String redirectStr = request.getContextPath() + "/result.jsp?username=foo&password=bar";
		String redirectStr = request.getContextPath() + "/getRequestData";
		System.out.println("Redirecting to " + redirectStr);
		
		response.sendRedirect(response.encodeRedirectURL(redirectStr));
	}

}
