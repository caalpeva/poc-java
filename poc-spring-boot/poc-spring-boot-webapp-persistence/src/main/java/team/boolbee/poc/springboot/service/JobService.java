package team.boolbee.poc.springboot.service;

import java.util.List;

import org.springframework.data.domain.Example;

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Status;

public interface JobService {
	List<Job> list();
	Job findBy(Integer id);
	List<Job> findByExample(Example<Job> job);
	void add(Job job);
	List<Job> findByFeaturedAndStatus(boolean featured, Status status);
	void delete(Integer id);	
}
