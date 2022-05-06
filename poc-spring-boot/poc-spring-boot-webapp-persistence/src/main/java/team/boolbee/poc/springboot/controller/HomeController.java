package team.boolbee.poc.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/")
	public String init(Model model) {
		List<Job> jobs = jobService.findByFeaturedAndStatus(true, Status.ACTIVE);
		model.addAttribute("categories", categoryService.list());
		model.addAttribute("jobs", jobs);
		model.addAttribute("message", "Bienvenido a la aplicación de empleos");
		model.addAttribute("fecha", new Date());
		return "home";
	}
	
	@GetMapping("/signup")
	public String register(User user) {
		return "users/create";
	}
	
	@PostMapping("/signup")
	public String save(User user, RedirectAttributes attributes) {
		userService.add(user);
		return "redirect:/users/index";
	}
}
