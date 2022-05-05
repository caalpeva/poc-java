package team.boolbee.poc.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.service.CategoryService;
import team.boolbee.poc.springboot.service.JobService;
import team.boolbee.poc.springboot.util.Utils;

@Controller
@RequestMapping(value="/jobs")
public class JobController {
	
	@Value("${team.boolbee.poc.jobs.file.location}")
	private String fileLocation;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/create")
	public String create(Job job, Model model) {
		model.addAttribute("categories", categoryService.list());
		return "jobs/create";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Job job, BindingResult result,
			RedirectAttributes redirectAttributes, @RequestParam("imageFile") MultipartFile multipartFile, Model model) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			model.addAttribute("categories", categoryService.list());
			return "jobs/create";
		}
		
		if (!multipartFile.isEmpty()) {
			String filename = Utils.saveFile(multipartFile, fileLocation);
			job.setImage(filename);
		}
		
		System.out.println("Job: " + job);
		jobService.add(job);
		redirectAttributes.addFlashAttribute("msg", "Registro guardado");
		return "redirect:/jobs/index";
	}
	
	@GetMapping("/index")
	public String list(Model model) {
		model.addAttribute("jobs", jobService.list());
		return "jobs/list";
	}
	
	@GetMapping("/detail/{id}")
	public String getDetail(@PathVariable("id") Integer id, Model model) {
		Job job = jobService.findBy(id);
		model.addAttribute("job", job);
		
		return "jobs/detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		List<Job> jobs = jobService.list();
		model.addAttribute("jobs", jobs);
		return "jobs/list";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));		
	}
}