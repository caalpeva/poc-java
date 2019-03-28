package team.boolbee.poc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team.boolbee.poc.spring.model.Role;
import team.boolbee.poc.spring.model.User;
import team.boolbee.poc.spring.repository.RoleRepository;
import team.boolbee.poc.spring.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/index")
	public String index(Model model, Authentication authentication) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("authentication", authentication);
		return "users/userList";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute User user) {
		return "users/userForm";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, BindingResult result) { //, @RequestParam("selectedProfiles") List<Integer> profileIds) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "users/userForm";
		}
		
		System.out.println(user);
//		for(int profile: profileIds) {
//			System.out.println(profile);
//		}
		
		//user.setProfiles(profileRepository.findAllById(profileIds));
		System.out.println(user);
		System.out.println("PASSWORD: " + user.getPassword());
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		System.out.println("ENCODED PASSWORD: " + encodedPassword);
		user.setPassword(encodedPassword);
		System.out.println("SAVED PASSWORD: " + user.getPassword());
		userService.save(user);
		System.out.println("SAVED PASSWORD ON DATABASE" + user.getPassword());
		
		return "redirect:/users/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int userId, Model model) {
		model.addAttribute("user", userService.findById(userId));
		return "users/userForm";
	}
	
	@GetMapping("/delete/{id}")
	public String create(@PathVariable("id") int userId) {
		userService.delete(userId);
		return "redirect:/users/index";
	}
	
	@ModelAttribute("profileList")
	public List<Role> getUserProfiles() {
		return roleRepository.findAll();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				 Role  p = new Role() ;
		         try {
		        	 if (element instanceof String) {
			             Integer id = new Integer(String.valueOf(element));
			              p = (Role) roleRepository.findById(id).get();
			             System.out.println(p.getId() + "\\" +p.getName());
		        	 }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return p;
			}
		});
	}
}