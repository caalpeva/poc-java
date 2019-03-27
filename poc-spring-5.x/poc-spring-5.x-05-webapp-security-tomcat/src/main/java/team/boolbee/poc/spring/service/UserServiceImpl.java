package team.boolbee.poc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.User;
import team.boolbee.poc.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByName(String username) {
		return userRepository.findByName(username);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(String username) {
		User user = userRepository.findByName(username);
		userRepository.delete(user);
	}
}