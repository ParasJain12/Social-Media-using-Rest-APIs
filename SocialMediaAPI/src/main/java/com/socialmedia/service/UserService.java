package com.socialmedia.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Value("${jsonplaceholder.api.users}")
	private String usersApi;

	public List<User> getAllUsers() {
		// Call external API to get user data
		ResponseEntity<User[]> responseEntity = new RestTemplate().getForEntity(usersApi, User[].class);
		User[] users = responseEntity.getBody();
		// save user to database
		userRepository.saveAll(Arrays.asList(users));
		return Arrays.asList(users);
	}

	public User getUserById(Long id) {
		// Try to find user in database
		return userRepository.findById(id).orElseGet(() -> {
			// Not found search using external APIs
			String userApiUrl = usersApi + "/" + id;
			ResponseEntity<User> responseEntity = new RestTemplate().getForEntity(usersApi, User.class);
			User user = responseEntity.getBody();
			// save the user on database
			if (user != null) {
				userRepository.save(user);
			}
			return user;
		});
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(Long id,User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUsername(updatedUser.getUsername());
			user.setEmail(updatedUser.getEmail());
			user.setName(updatedUser.getName());
			user.setBio(updatedUser.getBio());
			return userRepository.save(user);
		}).orElse(null);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
