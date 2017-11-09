package team.boobee.samples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.boobee.samples.webapp.utils.CookieFilter;
import team.boobee.samples.webapp.utils.HTMLFilter;

public class RequestDataServlet extends HttpServlet {

	private static final long serialVersionUID = 2254962368742772467L;

	//private static final ResourceBundle resources = ResourceBundle.getBundle("strings");

	PrintWriter out;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		out = response.getWriter();
		out.println("<html>");
		out.println("<head>");

		out.println("<title>Request Data</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		printDataTable("Request Information", getRequestInformation(request));
		printDataTable("Request Headers", getRequestHeaders(request));
		printDataTable("Request Parameters", getRequestParameters(request));
		printDataTable("Request Attributes", getRequestAttributes(request));
		
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
    /**************************************************************************/
    /*************************** METODOS PRIVADOS *****************************/
    /**************************************************************************/
	
	private Map<String, String> getRequestInformation(HttpServletRequest request) {
		Map<String, String> map = new TreeMap<>();
		map.put("Method", request.getMethod());
		map.put("Request URI", request.getRequestURI());
		map.put("Protocol", request.getProtocol());
		map.put("PathInfo", request.getPathInfo());
		map.put("Remote Address", request.getRemoteAddr());
		
		return map;
	}
	
	private Map<String, String> getRequestHeaders(HttpServletRequest request) {
		Map<String, String> map = new TreeMap<>();
		Enumeration<String> headers = request.getHeaderNames();
		if (headers != null) {
			while (headers.hasMoreElements()) {
				String name = headers.nextElement();
				String value = request.getHeader(name);
				if (name.toLowerCase(Locale.ENGLISH).contains("cookie")) {
					String sessionId = null;
					HttpSession session = request.getSession(false);
					if (session != null) {
						sessionId = session.getId();
					}
					value = CookieFilter.filter(value, sessionId);
				}
				map.put(name, value);
			} // while
		}
		
		return map;
	}
	
	private Map<String, String> getRequestParameters(HttpServletRequest request) {
		Map<String, String> map = new TreeMap<>();
		Enumeration<String> parameters = request.getParameterNames();
		if (parameters != null) {
			while(parameters.hasMoreElements()) {
				String name = parameters.nextElement();
				map.put(name, request.getParameter(name));
			} // while
		}
		
		return map;
	}
	
	private Map<String, String> getRequestAttributes(HttpServletRequest request) {
		Map<String, String> map = new TreeMap<>();
		Enumeration<String> atributtes = request.getAttributeNames();
		if (atributtes != null && atributtes.hasMoreElements()) {
			while(atributtes.hasMoreElements()) {
				String name = atributtes.nextElement();
				map.put(name, (String) request.getAttribute(name));
			} // while
		}
		
		return map;
	}
	
	private void printDataTable(String title, Map<String, String> map) {
		out.println("<h3>" + title + "</h3>");
		if (map != null && map.size() > 0) {
			out.println("<table border=0>");
			for(String text: map.keySet()) {
				out.println("<tr><td bgcolor=\"#CCCCCC\">");
				out.println(HTMLFilter.filter(text));
				out.println("</td><td>");
				out.println(HTMLFilter.filter(map.get(text)));
				out.println("</td></tr>");
			} // for
			out.println("</table>");
		} else {
			out.println("No data");
		}
	}
}