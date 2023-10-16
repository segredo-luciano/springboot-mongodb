package com.segredo.webservicemongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.segredo.webservicemongodb.domain.User;
import com.segredo.webservicemongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {		
		userRepository.deleteAll();
		
		User user = new User(null, "Maria Brown", "mary23@gmail.com");
		User userT = new User(null, "Alex Green", "lelex00@gmail.com");
		User userTh = new User(null, "Bob Grey", "bobb@gmail.com");
		
		userRepository.saveAll(Arrays.asList(user, userT, userTh));
	}

}
