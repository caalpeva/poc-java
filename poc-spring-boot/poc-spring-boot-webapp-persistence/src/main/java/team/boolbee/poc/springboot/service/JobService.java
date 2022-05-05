package team.boolbee.poc.springboot.service;

import java.util.List;

import team.boolbee.poc.springboot.model.Job;

public interface JobService {
	List<Job> list();
	Job findBy(Integer id);
	void add(Job job);
}
