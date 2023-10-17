package com.segredo.webservicemongodb.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.segredo.webservicemongodb.domain.Post;
import com.segredo.webservicemongodb.domain.User;
import com.segredo.webservicemongodb.repository.PostRepository;
import com.segredo.webservicemongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user = new User(null, "Maria Brown", "mary23@gmail.com");
		User userT = new User(null, "Alex Green", "lelex00@gmail.com");
		User userTh = new User(null, "Bob Grey", "bobb@gmail.com");
		
		LocalDate dateCustom = LocalDate.parse("21/07/2023", dtf);
		LocalDate dateCustomT = LocalDate.parse("23/03/2023", dtf);

		Post post = new Post(null, dateCustom, "to travel", "Going to travel to São Paulo. Bye", user);
		Post postT = new Post(null, dateCustomT, "Morning", "Wake up while the sun still bright!", user);
		
		userRepository.saveAll(Arrays.asList(user, userT, userTh));
		postRepository.saveAll(Arrays.asList(post, postT));
	}

}
