package team.boolbee.poc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.service.MovieService;
import team.boolbee.poc.spring.utils.Utils;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/index")
	public String goIndex(Model model) {
		model.addAttribute("movies", movieService.findAll());
		return "movies/movieList";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute Movie movie, Model model) {
		//model.addAttribute("movieTypes", movieService.getMovieTypes());
		return "movies/movieForm";
	}
	
	@GetMapping(value="/edit/{id}")
	public String create(@PathVariable("id") int movieId, Model model) {
		Movie movie = movieService.findById(movieId);
		model.addAttribute("movie", movie);
		//model.addAttribute("movieTypes", movieService.getMovieTypes());
		return "movies/movieForm";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Movie movie, BindingResult result, RedirectAttributes redirectAttributes,
			@RequestParam("imageFile") MultipartFile multipartFile, HttpServletRequest request, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			//model.addAttribute("movieTypes", movieService.getMovieTypes());
			return "movies/movieForm";
		}

		if (!multipartFile.isEmpty()) {
			String filename = Utils.saveImage(multipartFile, request);
			movie.setFilename(filename);
		}

		System.out.println(movie);
		movieService.save(movie);

		redirectAttributes.addFlashAttribute("successMessage", "El registro fue guardado");
		return "redirect:/movies/index"; // URL relativa al context path de la aplicación
	}

	@ModelAttribute("movieTypes")
	public List<FilmType> getMovieTypes() {
		return movieService.getMovieTypes();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}