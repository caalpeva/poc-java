package team.boolbee.poc.springboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import team.boolbee.poc.springboot.model.Category;
import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Profile;
import team.boolbee.poc.springboot.model.Status;
import team.boolbee.poc.springboot.model.User;
import team.boolbee.poc.springboot.repository.CategoryRepository;
import team.boolbee.poc.springboot.repository.JobRepository;
import team.boolbee.poc.springboot.repository.ProfileRepository;
import team.boolbee.poc.springboot.repository.UserRepository;

@SpringBootApplication
public class DemoApplication implements	CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Esto es una prueba");
		//addCategory();
		//addJob();
		countCategories();
		listCategories();
		countJobs();
		listJobs();
		getCategoryById(2);
		getCategoryById(20);
		getJobById(5);
		getJobById(50);
		System.out.println("Despues de insertar datos");
		//deleteJob(1);
		//deleteCategory(1);
		//deleteAllJobsInBatch();
		System.out.println("Despues de eliminar");
		updateJob(5);
		System.out.println("Pruebas de ordenamiento y paginacion");
		sortJobs();
		pageJobs();
		System.out.println("Guardar perfiles y usuario");
		/*savePerfiles();
		addUserWithTwoProfiles();
		listUsers();
		findUserById(1);*/
		findByStatus();
		findByManyStatus();
		findByFeaturedAndStatusOrderByIdDesc();
		findBySalaryBetweenOrderSalaryDesc();
	}
	
	@SuppressWarnings("unused")
	private void addCategory() {
		Category category = new Category();
		category.setName("Contabilidad");
		category.setDescription("Tareas de contabilidad y finanzas");
		categoryRepository.save(category);
	}
	
	@SuppressWarnings("unused")
	private void addJob() {
		Job job = new Job();
		job.setName("Contable");
		job.setDescription("Realizar labores de contabilidad empresarial");
		job.setImage("no-image.png");
		job.setPublicationDate(new Date());
		job.setFeatured(true);
		job.setSalary(25000.0);
		job.setStatus(Status.ACTIVE);
		job.setDetails("Beneficios sociales, tickets restaurant, seguro médico");
		Category category = new Category();
		category.setId(1);
		job.setCategory(category);
		jobRepository.save(job);
	}
	
	private void countJobs() {
		System.out.println("Job count: " + jobRepository.count());
	}
	
	private void countCategories() {
		System.out.println("Category count: " + categoryRepository.count());
	}
	
	private void getCategoryById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			System.out.println(category.get());
		} else {
			System.out.println("Category Not found");
		}
	}
	
	private void getJobById(Integer id) {
		Optional<Job> job = jobRepository.findById(id);
		if (job.isPresent()) {
			System.out.println(job.get());
		} else {
			System.out.println("Job Not found");
		}
	}

	private void listCategories() {
		System.out.println("---Categorias---");
		for(Category c: categoryRepository.findAll()) {
			System.out.println(c.getId() + ", " + c.getName());
		}
	}
	
	private void listJobs() {
		System.out.println("---JOBS---");
		for(Job c: jobRepository.findAll()) {
			System.out.println(c.getId() + ", " + c.getName() + ", categoria: " +  c.getCategory().getName());
		}
	}
	
	@SuppressWarnings("unused")
	private void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	@SuppressWarnings("unused")
	private void deleteJob(Integer id) {
		jobRepository.deleteById(id);
	}
	
	@SuppressWarnings("unused")
	private void deleteAllJobsInBatch() {
		//jobRepository.deleteAll();
		jobRepository.deleteAllInBatch();
	}
	
	private void updateJob(Integer id) {
		Optional<Job> job = jobRepository.findById(id);
		if (job.isPresent()) {
			Job entity = job.get();
			System.out.println(entity);			
			entity.setName("Futbolista");
			System.out.println(job.get());
			System.out.println("After updating");
			jobRepository.save(entity);
			Optional<Job> job2 = jobRepository.findById(id);
			if (job2.isPresent()) {
				System.out.println(job2);
			}
		} else {
			System.out.println("Job Not found");
		}
	}
	
	private void sortJobs() {
		System.out.println("---ORDERED JOBS---");
		for(Job c: jobRepository.findAll(Sort.by("name").descending())) {
			System.out.println(c.getId() + ", " + c.getName() + ", categoria: " +  c.getCategory().getName());
		}
	}
	
	private void pageJobs() {
		System.out.println("---PAGED JOBS---");
		Page<Job> pageJobs = jobRepository.findAll(PageRequest.of(0, 5, Sort.by("name").descending()));
		System.out.println("Elements: " + pageJobs.getNumberOfElements());
		System.out.println("Pages: " + pageJobs.getTotalPages());
		for(Job c: pageJobs) {
			System.out.println(c.getId() + ", " + c.getName() + ", categoria: " +  c.getCategory().getName());
		}
	}
	
	@SuppressWarnings("unused")
	private void savePerfiles() {
		List<Profile> profiles = new ArrayList<Profile>();
		Profile profile = new Profile();
		profile.setName("Supervisor");
		profiles.add(profile);
		profile = new Profile();
		profile.setName("Administrador");
		profiles.add(profile);
		profile = new Profile();
		profile.setName("Usuario");
		profiles.add(profile);
		profileRepository.saveAll(profiles);
	}
	
	@SuppressWarnings("unused")
	private void addUserWithTwoProfiles() {
		User user = new User();
		user.setName("Perico");
		user.setEmail("perico@gmail.com");
		user.setUsername("perico");
		user.setPassword("perico");
		user.setStatus("Activo");
		user.setRegistrationDate(new Date());
		Profile profile1 = new Profile();
		profile1.setId(2);
		user.addProfile(profile1);
		Profile profile2 = new Profile();
		profile2.setId(3);
		user.addProfile(profile2);
		userRepository.save(user);
	}
	
	@SuppressWarnings("unused")
	private void listUsers() {
		List<User> users = userRepository.findAll();
		for(User u: users) {
			System.out.println(u);
			System.out.println("-----");
			
			for(Profile p: u.getProfiles()) {
				System.out.println(p.getId() + " -" + p.getName());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void findUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User u = user.get(); 
			System.out.println(u);
			System.out.println("-----");		
			for(Profile p: u.getProfiles()) {
				System.out.println(p.getId() + " -" + p.getName());
			}
		}
	}
	
	private void findByStatus() {
		System.out.println("---JOBS BY STATUS---");
		List<Job> jobs = jobRepository.findByStatus(Status.ACTIVE);
		System.out.println("Numero de jobs: " + jobs.size());
		for(Job c: jobs) {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.getStatus());
		}
	}
	
	private void findByManyStatus() {
		System.out.println("---JOBS BY STATUS IN---");
		Status[] status = new Status[] {Status.ACTIVE, Status.INACTIVE};
		List<Job> jobs = jobRepository.findByStatusIn(status);
		System.out.println("Numero de jobs: " + jobs.size());
		for(Job c: jobs) {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.getStatus());
		}
	}
	
	private void findByFeaturedAndStatusOrderByIdDesc() {
		System.out.println("---JOBS BY FEATURED AND STATUS ORDER BY ID DESC---");
		List<Job> jobs = jobRepository.findByFeaturedAndStatusOrderByIdDesc(true, Status.ACTIVE);
		System.out.println("Numero de jobs: " + jobs.size());
		for(Job c: jobs) {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.isFeatured() + ", " + c.getStatus());
		}
	}
	
	private void findBySalaryBetweenOrderSalaryDesc() {
		System.out.println("---JOBS BY SALARY BETWEEN ORDER BY SALARY DESC---");
		List<Job> jobs = jobRepository.findBySalaryBetweenOrderBySalaryDesc(7000, 14000);
		System.out.println("Numero de jobs: " + jobs.size());
		for(Job c: jobs) {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.getSalary());
		}
	}
}
