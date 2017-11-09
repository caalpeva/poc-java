package team.boobee.samples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClockServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public ClockServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		//response.getWriter().append(dateFormat.format(Calendar.getInstance().getTime()));
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println(String.format("<meta http-equiv='refresh' content='1; url=http://%s%s%s'>",
				request.getHeader("host"),
				request.getContextPath(), 
				request.getServletPath()));
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + dateFormat.format(Calendar.getInstance().getTime()) + "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
