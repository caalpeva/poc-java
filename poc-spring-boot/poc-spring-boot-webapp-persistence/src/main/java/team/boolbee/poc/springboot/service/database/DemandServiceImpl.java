package team.boolbee.poc.springboot.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Demand;
import team.boolbee.poc.springboot.repository.DemandRepository;
import team.boolbee.poc.springboot.service.DemandService;

@Service
public class DemandServiceImpl implements DemandService {

	@Autowired
	private DemandRepository demandRepository;
	
	@Override
	public void save(Demand demand) {
		demandRepository.save(demand);		
	}

	@Override
	public void delete(Integer id) {
		demandRepository.deleteById(id);
	}

	@Override
	public List<Demand> list() {
		return demandRepository.findAll();
	}

	@Override
	public Page<Demand> list(Pageable page) {
		return demandRepository.findAll(page);
	}

	@Override
	public Demand findById(Integer id) {
		Optional<Demand> demand = demandRepository.findById(id);
		if (demand.isPresent()) {
			return demand.get();
		}
		
		return null;
	}
}