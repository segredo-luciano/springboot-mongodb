package com.segredo.webservicemongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segredo.webservicemongodb.domain.User;
import com.segredo.webservicemongodb.dto.UserDTO;
import com.segredo.webservicemongodb.repository.UserRepository;
import com.segredo.webservicemongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
				
		return user.orElseThrow(() -> new ObjectNotFoundException("this id does not exist! id: " + id));		
	}
	
	public User save(User user) {
		return userRepository.insert(user);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
	public void delete(String id) {
		userRepository.deleteById(id);
	}
	
	public User update(String id, String name, String email) {
		try {
			User existingUser = findById(id);
			
			existingUser.setName(name);
			existingUser.setEmail(email);
			
			return userRepository.save(existingUser);
		} catch(Exception enfe) {
			throw new ObjectNotFoundException("There is no object with the given id");
		}
	}
}
