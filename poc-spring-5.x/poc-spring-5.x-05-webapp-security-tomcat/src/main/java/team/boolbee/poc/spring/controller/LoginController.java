package team.boolbee.poc.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@GetMapping("/index")
	public String goMainPage() {
//		System.out.println("Username: " + authentication.getName());
//		for(GrantedAuthority rol: authentication.getAuthorities()) {
//			System.out.println("Rol: " + rol.getAuthority());
//		} // for
		return "admin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin/login";
	}

}