package team.boolbee.poc.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team.boolbee.poc.springboot.model.Job;
import team.boolbee.poc.springboot.model.Status;

public interface JobRepository extends JpaRepository<Job, Integer> {
	
	List<Job> findByStatus(Status status);	
	List<Job> findByStatusIn(Status[] status);
	List<Job> findByFeaturedAndStatusOrderByIdDesc(boolean featured, Status status);
	List<Job> findBySalaryBetweenOrderBySalaryDesc(double s1, double s2);
	
}
