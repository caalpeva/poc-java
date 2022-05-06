package team.boolbee.poc.springboot.service.memory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Category;
import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Status;
import team.boolbee.poc.springboot.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private List<Job> jobs = new LinkedList<Job>();

	public JobServiceImpl() {
		try {
			
			Job job1 = new Job();
			job1.setId(1);
			job1.setName("Ingeniero");
			job1.setDescription("Se necesita ingeniero para dar soporte a Intranet");
			job1.setPublicationDate(sdf.parse("2022-04-11"));
			job1.setSalary(1500.0);
			job1.setFeatured(true);
			Category categoria = new Category();
			categoria.setName("Transporte");
			categoria.setId(0);
			categoria.setDescription("Transportistas");
			job1.setCategory(categoria);
			job1.setImage("altran_logo.png");
			
			Job job2 = new Job();
			job2.setId(2);
			job2.setName("Diseñador gráfico");
			job2.setDescription("Se necesita diseñador titulado para dar diseñar la web empresarial");
			job2.setPublicationDate(sdf.parse("2022-04-22"));
			job2.setSalary(1200.0);
			job2.setFeatured(false);
			job2.setImage("orange.png");
			
			Job job3 = new Job();
			job3.setId(3);
			job3.setName("Camarero");
			job3.setDescription("Se necesita camarero para incorporación inmediata");
			job3.setPublicationDate(sdf.parse("2022-04-25"));
			job3.setSalary(900.0);
			job3.setFeatured(false);
			
			Job job4 = new Job();
			job4.setId(4);
			job4.setName("Electricista");
			job4.setDescription("Se necesita electricista para control de calidad");
			job4.setPublicationDate(new Date());
			job4.setSalary(1100.0);
			job4.setFeatured(true);
			job4.setImage("iberdrola.jpg");
			
			jobs.add(job1);
			jobs.add(job2);
			jobs.add(job3);
			jobs.add(job4);
		} catch(java.text.ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	@Override
	public List<Job> list() {
		return jobs;
	}

	@Override
	public Job findBy(Integer id) {
		for (Job job : jobs) {
			if (job.getId().equals(id)) {
				return job;
			}
		}
		
		return null;
	}

	@Override
	public void add(Job job) {
		job.setId(getLastId() + 1);
		jobs.add(job);		
	}
	
	private Integer getLastId() {
		return jobs.size()> 0 ? jobs.get(jobs.size() - 1).getId(): 0;
	}

	@Override
	public List<Job> findByFeaturedAndStatus(boolean featured, Status status) {
		return null;
	}

	@Override
	public void delete(Integer id) {				
	}

	@Override
	public List<Job> findByExample(Example<Job> job) {
		// TODO Auto-generated method stub
		return null;
	}
}
