package com.restful.webservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.webservices.beans.User;
import com.restful.webservices.dao.UserDao;
import com.restful.webservices.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userDao.findUser(id);
		if (user == null) throw new UserNotFoundException("User ID: " + id);
		
		//HATEOAS - Hypermedia As The Engine Of Application State
		//
		
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDao.deleteById(id);
		
		if (user == null) throw new UserNotFoundException("User ID: " + id);
		
	}
	
	
}
