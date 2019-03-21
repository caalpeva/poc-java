package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.User;

public interface UserService {
	public List<User> findAll();
	public User findById(int userId);
	public void save(User user);
	public void delete(int userId);
}