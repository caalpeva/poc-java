package team.boolbee.poc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public User findByName(String name);
}
