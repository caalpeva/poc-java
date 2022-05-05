package team.boolbee.poc.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.service.JobService;

@Controller
public class HomeController {
	
	@Autowired
	public JobService jobService;
	
	@GetMapping()
	public String inicio(Model model) {
		List<Job> jobs = jobService.list();
		model.addAttribute("jobs", jobs);
		model.addAttribute("message", "Bienvenido a la aplicación de empleos");
		model.addAttribute("fecha", new Date());
		return "home";
	}
}
