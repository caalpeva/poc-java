package team.boolbee.poc.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.User;

@Service
public interface UserService {
	void add(User user);
	void delete(Integer id);
	List<User> list();
	User findByUsername(String username);
}