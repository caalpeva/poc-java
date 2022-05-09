package team.boolbee.poc.springboot.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.extras.springsecurity5.auth.Authorization;

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Status;
import team.boolbee.poc.springboot.model.User;
import team.boolbee.poc.springboot.service.CategoryService;
import team.boolbee.poc.springboot.service.JobService;
import team.boolbee.poc.springboot.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("jobServiceInDatabase")
	public JobService jobService;
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String init(Job job, Model model) {
		List<Job> jobs = jobService.findByFeaturedAndStatus(true, Status.ACTIVE);
		model.addAttribute("categories", categoryService.list());
		model.addAttribute("jobs", jobs);
		model.addAttribute("message", "Bienvenido a la aplicación de empleos");
		model.addAttribute("fecha", new Date());
		return "home";
	}
	
	@PostMapping("/search")
	public String search(@ModelAttribute Job job, Model model) {
		job.resetImage();
		System.out.println(job);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Job> example = Example.of(job, exampleMatcher);
		List<Job> jobs = jobService.findByExample(example);
		model.addAttribute("categories", categoryService.list());
		model.addAttribute("jobs", jobs);
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signin")
	public String signin(Authentication authentication, HttpSession session) {
		String username = authentication.getName();
		System.out.println("username: " +  username);
		for(GrantedAuthority authority: authentication.getAuthorities()) {
			System.out.println("ROLE: " + authority);
		}
		
		if (session.getAttribute("user") == null) {
			User user = userService.findByUsername(username);
			user.setPassword(null);
			System.out.println(user);
			session.setAttribute("user", user);			
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup(User user) {
		return "users/create";
	}
	
	@PostMapping("/signup")
	public String save(User user, RedirectAttributes attributes) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.add(user);
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		return "redirect:/login"; // "redirect:/signin";
	}
	
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/signin";
	}
	
	@GetMapping("/encode/{text}")
	@ResponseBody
	public String encode(@PathVariable("text") String text) {	
		String encriptado = passwordEncoder.encode(text);
		System.out.println("Password encriptado: " + encriptado);
		return encriptado;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
