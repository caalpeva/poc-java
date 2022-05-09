package team.boolbee.poc.springboot.service.database;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Profile;
import team.boolbee.poc.springboot.model.Status;
import team.boolbee.poc.springboot.model.User;
import team.boolbee.poc.springboot.repository.ProfileRepository;
import team.boolbee.poc.springboot.repository.UserRepository;
import team.boolbee.poc.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public void add(User user) {
		user.setRegistrationDate(new Date());
		user.setStatus(Status.ACTIVE);
		Profile profile = new Profile();
		profile.setId(3);
		user.addProfile(profile);
		userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}