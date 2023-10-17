package com.segredo.webservicemongodb.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.segredo.webservicemongodb.domain.Post;
import com.segredo.webservicemongodb.domain.User;
import com.segredo.webservicemongodb.dto.AuthorDTO;
import com.segredo.webservicemongodb.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(user, userT, userTh));
		
		LocalDate dateCustom = LocalDate.parse("21/07/2023", dtf);
		LocalDate dateCustomT = LocalDate.parse("23/03/2023", dtf);

		Post post = new Post(null, dateCustom, "to travel", "Going to travel to São Paulo. Bye", new AuthorDTO(user));
		Post postT = new Post(null, dateCustomT, "Morning", "Wake up while the sun still bright!", new AuthorDTO(user));
			
		CommentDTO comment = new CommentDTO("Good travel ma man", LocalDate.parse("21/03/2023", dtf), new AuthorDTO(userT));
		CommentDTO commentT = new CommentDTO("Enjoy it", LocalDate.parse("22/03/2023", dtf), new AuthorDTO(userTh));
		CommentDTO commentTh = new CommentDTO("Have a nice day G", LocalDate.parse("23/03/2023", dtf), new AuthorDTO(userT));
		
		post.getComments().addAll(Arrays.asList(comment, commentT));
		postT.getComments().addAll(Arrays.asList(commentTh));
		
		postRepository.saveAll(Arrays.asList(post, postT));
		
		user.getPosts().addAll(Arrays.asList(post, postT));
		userRepository.save(user);
	}

}
