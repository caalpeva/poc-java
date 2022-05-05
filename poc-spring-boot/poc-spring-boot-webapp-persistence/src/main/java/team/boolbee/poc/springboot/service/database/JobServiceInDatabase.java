package team.boolbee.poc.springboot.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Status;
import team.boolbee.poc.springboot.repository.JobRepository;
import team.boolbee.poc.springboot.service.JobService;

@Service
public class JobServiceInDatabase implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public List<Job> list() {
		return jobRepository.findAll();
	}

	@Override
	public Job findBy(Integer id) {
		Optional<Job> job = jobRepository.findById(id);
		if (job.isPresent()) {
			return job.get();
		}
		
		return null;
	}

	@Override
	public void add(Job job) {
		jobRepository.save(job);
	}

	@Override
	public List<Job> findByFeaturedAndStatus(boolean featured, Status status) {
		return jobRepository.findByFeaturedAndStatusOrderByIdDesc(featured, status);
	}

	@Override
	public void delete(Integer id) {
		jobRepository.deleteById(id);
	}

}
