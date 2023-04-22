package team.boolbee.poc.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.boolbee.poc.springboot.model.Demand;

public interface DemandService {
	
	void save(Demand demand);
	void delete(Integer id);
	List<Demand> list();
	Page<Demand> list(Pageable page);
	Demand findById(Integer id);
}
