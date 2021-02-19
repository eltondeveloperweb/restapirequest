package br.com.microservice.course.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.dto.UserLoginDto;
import br.com.microservice.course.service.RequestService;
import br.com.microservice.course.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	private UserService userService;
	private RequestService RequestService;

	@Autowired
	public UserResource(UserService userService, RequestService requestService) {
		this.userService = userService;
		this.RequestService = requestService;
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		
		User createUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user){
		
		user .setId(id);
		User updateUser = userService.Update(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		
		User user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		
		List<User> users = userService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto user){
		
		User logged = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(logged);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable("id") Long id){
		
		List<Request> requests = RequestService.listAllByOwnerId(id);
		return ResponseEntity.status(HttpStatus.OK).body(requests);
	}

}
