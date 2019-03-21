package team.boolbee.poc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.boolbee.poc.spring.model.Profile;
import team.boolbee.poc.spring.model.User;
import team.boolbee.poc.spring.repository.ProfileRepository;
import team.boolbee.poc.spring.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@GetMapping("/create")
	public String create(@ModelAttribute User user) {
		return "users/userForm";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, @RequestParam("profiles") int[] profileIds) {
		System.out.println(user);
		System.out.println(profileIds);
		return "redirect:/users/index";
	}
	
	@ModelAttribute("profileList")
	public List<Profile> getUserProfiles() {
		return profileRepository.findAll();
	}

}
