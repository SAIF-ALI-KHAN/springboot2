package net.guides.springboot2.springboot2swagger2.serviceImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2swagger2.model.User;
import net.guides.springboot2.springboot2swagger2.repository.UserRepository;
import net.guides.springboot2.springboot2swagger2.service.UserService;


@Service
@Transactional
public class UserServiceImp  implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public User getUser(Integer userID) {
		// TODO Auto-generated method stub
		User User=null;
		try {
			User = userRepository.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " +userID));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return User;
	}

}
