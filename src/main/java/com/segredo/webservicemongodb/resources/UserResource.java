package com.segredo.webservicemongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.segredo.webservicemongodb.domain.Post;
import com.segredo.webservicemongodb.domain.User;
import com.segredo.webservicemongodb.dto.UserDTO;
import com.segredo.webservicemongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {		
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {		
		User user= userService.findById(id);		
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody UserDTO UserDTO) {
		User user = userService.fromDTO(UserDTO);
		user = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @RequestParam String name, @RequestParam String email) {
		User user = userService.update(id, name, email);
		
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findByPosts(@PathVariable String id) {		
		User user= userService.findById(id);		
		
		return ResponseEntity.ok().body(user.getPosts());
	}
}
