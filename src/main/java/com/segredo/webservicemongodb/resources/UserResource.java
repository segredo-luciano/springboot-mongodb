package com.segredo.webservicemongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segredo.webservicemongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User user = new User("1", "Maria", "mary@gmail.com");
		User userT = new User("2", "Alex", "lelex@gmail.com");
		List<User> list = new ArrayList<>();
		
		list.addAll(Arrays.asList(user, userT));
		
		return ResponseEntity.ok().body(list);
	}
}
