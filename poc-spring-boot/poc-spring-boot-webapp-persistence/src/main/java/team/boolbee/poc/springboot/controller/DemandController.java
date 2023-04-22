package team.boolbee.poc.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import team.boolbee.poc.springboot.model.Demand;
import team.boolbee.poc.springboot.model.User;
import team.boolbee.poc.springboot.service.DemandService;
import team.boolbee.poc.springboot.service.JobService;
import team.boolbee.poc.springboot.service.UserService;
import team.boolbee.poc.springboot.util.Utils;

@Controller
@RequestMapping("/demands")
public class DemandController {
	
	@Value("${team.boolbee.poc.jobs.location.files}")
	private String fileLocation;
	
	@Autowired
	private DemandService demandService;
	
	@Autowired
	@Qualifier("jobServiceInDatabase")
	private JobService jobService;
	
	@Autowired
	private UserService userService;
			
    @GetMapping("/index") 
	public String mostrarIndex(Model model) {
    	model.addAttribute("demands", demandService.list());
		return "demands/list";
		
	}
    
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {		
		model.addAttribute("demands", demandService.list(page));
		return "demands/list";
		
	}
    
	@GetMapping("/create/{id}")
	public String create(@PathVariable("id") Integer id, Demand demand, Model model) {
		model.addAttribute("job", jobService.findBy(id));
		return "demands/create";
		
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Demand demand, BindingResult result,
			RedirectAttributes redirectAttributes, @RequestParam("curriculumfile") MultipartFile multipartFile, Authentication authentication) {	
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "demands/create";
		}
		
		if (!multipartFile.isEmpty()) {
			String filename = Utils.saveFile(multipartFile, fileLocation);
			demand.setFile(filename);
		}
		
		if (authentication.isAuthenticated()) {
			User user = userService.findByUsername(authentication.getName());
			demand.setUser(user);
		}
		
		demand.setDate(new Date());
		System.out.println("Demand: " + demand);
		demandService.save(demand);
		redirectAttributes.addFlashAttribute("msg", "Registro guardado");
		return "redirect:/";	
		
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		demandService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "Registro eliminado");
		return "redirect:/demands/indexPaginate";
		
	}
			
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
