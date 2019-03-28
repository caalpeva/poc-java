package team.boolbee.poc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
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
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/userList";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute User user) {
		return "users/userForm";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "users/userForm";
		}
		
		System.out.println(user);
		System.out.println("PASSWORD: " + user.getPassword());
		String encodedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		System.out.println("ENCODED PASSWORD: " + encodedPassword);
		user.setPassword(encodedPassword);
		System.out.println("SAVED PASSWORD: " + user.getPassword());
		userService.save(user);
		System.out.println("SAVED PASSWORD ON DATABASE" + user.getPassword());
		
		return "redirect:/users/index";
	}

	@GetMapping("/edit/{name}")
	public String edit(@PathVariable("name") String username, Model model) {
		model.addAttribute("user", userService.findByName(username));
		return "users/userForm";
	}
	
	@GetMapping("/delete/{name}")
	public String create(@PathVariable("name") String username) {
		userService.delete(username);
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
				 Role  p = new Role();
		         try {
		        	 if (element instanceof String) {
		        		 p = (Role) roleRepository.findByName(String.valueOf(element));
			             System.out.println(p.getName());
		        	 }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return p;
			}
		});
	}
}