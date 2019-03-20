package team.boolbee.poc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@GetMapping("/index")
	public String goIndex(Model model) {
		model.addAttribute("newsList", newsService.findAll());
		return "news/newsList";
	}

	@GetMapping("/create")
	public String create() {

		return "news/newsForm";
	}

	@PostMapping("/save")
	public String save(News news, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "news/newsForm";
		}
		
		System.out.println(news);
		newsService.save(news);

		redirectAttributes.addFlashAttribute("successMessage", "El registro fue guardado");
		return "redirect:/news/index"; // URL relativa al context path de la aplicación
	}
	
}
