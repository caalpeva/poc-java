package team.boolbee.poc.spring.service;

import java.util.List;
import java.util.Optional;

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
	public User findById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		return optional.isPresent()? optional.get(): null;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(int userId) {
		userRepository.deleteById(userId);
	}
}