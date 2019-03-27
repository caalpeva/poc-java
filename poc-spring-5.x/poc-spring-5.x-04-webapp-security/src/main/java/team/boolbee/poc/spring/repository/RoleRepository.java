package team.boolbee.poc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
