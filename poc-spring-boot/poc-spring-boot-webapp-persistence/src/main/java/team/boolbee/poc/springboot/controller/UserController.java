package team.boolbee.poc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.boolbee.poc.springboot.service.UserService;

@Controller
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String list(Model model) {
		model.addAttribute("users", userService.list());
		return "users/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		userService.delete(id);
		return "redirect:/users/index";
	}

}
