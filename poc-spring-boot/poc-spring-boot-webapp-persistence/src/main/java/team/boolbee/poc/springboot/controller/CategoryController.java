package team.boolbee.poc.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String save(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("description") String description, Model model) {
		Category category = new Category();
		if (id != null && !id.isEmpty()) {
			category.setId(Integer.valueOf(id));
		}
		category.setName(name);
		category.setDescription(description);
		System.out.println("Category: " + category);
		categoryService.add(category);
		return "redirect:/categories/indexPaginate";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("category", categoryService.findBy(id));
		return "categories/create";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		categoryService.delete(id);
		return "redirect:/categories/indexPaginate";
	}
	
	
	@GetMapping("/index")
	public String list(Model model) {
		List<Category> categories = categoryService.list();
		model.addAttribute("categories", categories);
		return "categories/list";
	}
	
	@GetMapping("/indexPaginate")
	public String listPaginate(Model model, Pageable page) {
		Page<Category> categories = categoryService.list(page);
		model.addAttribute("categories", categories);
		return "categories/list";
	}
}