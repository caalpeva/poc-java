package team.boolbee.poc.spring.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Role;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.users.MemoryUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@GetMapping("/index")
	public String goMainPage(HttpSession session, Principal principal) {
		if (session.getAttribute("username") == null) {
			System.out.println("Username: " + principal.getName());
			session.setAttribute("username", principal.getName());

			List<String> roles = new ArrayList<String>();
			// Se realiza un CAST de la interfaz java.security.Principal a la implementación GenericPrincipal
			// de Apache Tomcat (Realm). De esta forma, se permite el acceso a los roles del usuario.
			if (principal instanceof GenericPrincipal) {
				GenericPrincipal generic = (GenericPrincipal) principal;
				roles.addAll(Arrays.asList(generic.getRoles()));
			} else if (principal instanceof MemoryUser) {
				MemoryUser generic = (MemoryUser) principal;
				for(Iterator<Role> it = generic.getRoles(); it.hasNext(); ) {
					roles.add(it.next().getRolename());
				} // for				
			}
			
			for(String role: roles) {
				System.out.println("Rol: " + role);
			} // for
			
			session.setAttribute("roles", roles);
		}

		return "admin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}