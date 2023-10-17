package com.segredo.webservicemongodb.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segredo.webservicemongodb.domain.Post;
import com.segredo.webservicemongodb.repository.PostRepository;
import com.segredo.webservicemongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {		
		Optional<Post> post = postRepository.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException("this id does not exist! id: " + id));
	}
	
	public List<Post> findByTitle(String text) {
//		return postRepository.findByTitleContainingIgnoreCase(text);
		return postRepository.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate) {
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
