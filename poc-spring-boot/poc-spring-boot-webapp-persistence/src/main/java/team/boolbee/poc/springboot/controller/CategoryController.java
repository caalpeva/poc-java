package team.boolbee.poc.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.boolbee.poc.springboot.model.Category;
import team.boolbee.poc.springboot.service.CategoryService;

@Controller
@RequestMapping(value="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/create")
	public String create(Category category) {
		return "categories/create";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("name") String name, @RequestParam("description") String description, Model model) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		System.out.println("Category: " + category);
		categoryService.add(category);
		return "redirect:/categories/index";
	}
	
	@GetMapping("/index")
	public String list(Model model) {
		List<Category> categories = categoryService.list();
		model.addAttribute("categories", categories);
		return "categories/list";
	}
}