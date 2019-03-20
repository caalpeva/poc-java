package team.boolbee.poc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team.boolbee.poc.spring.model.Showtimes;
import team.boolbee.poc.spring.service.MovieService;

@Controller
@RequestMapping(value="/showtimes")
public class ShowtimesController {
	
	@Autowired
	private MovieService movieService;
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo horario
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute("showtimes") Showtimes showtimes, Model model) {
		model.addAttribute("movies", movieService.findAll());
		return "showtimes/showtimesForm";
	}
		
	/**
	 * Metodo para guardar el registro del Horario
	 * @param horario
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute("showtimes") Showtimes showtimes, BindingResult result, Model model) {				
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("movies", movieService.findAll());
			return "showtimes/showtimesForm";
		}
		
		System.out.println(showtimes);
				
		// De momento, hacemos un redirect al mismo formulario 
		return "redirect:/showtimes/create";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}	
}