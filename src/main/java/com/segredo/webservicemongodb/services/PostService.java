package com.segredo.webservicemongodb.services;

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
}
