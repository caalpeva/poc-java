package team.boolbee.poc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team.boolbee.poc.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
