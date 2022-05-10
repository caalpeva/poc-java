package team.boolbee.poc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.boolbee.poc.springboot.model.Demand;

public interface DemandRepository extends JpaRepository<Demand, Integer> {

}
