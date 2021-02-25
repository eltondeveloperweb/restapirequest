package br.com.microservice.course.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.dto.UserLoginDto;
import br.com.microservice.course.dto.UserSaveDto;
import br.com.microservice.course.dto.UserUpdateDto;
import br.com.microservice.course.dto.UserUpdateRoleDto;
import br.com.microservice.course.model.PageModel;
import br.com.microservice.course.model.PageRequestModel;
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
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userdto){
		
		User userToSave = userdto.transformToUser();
		
		User createUser = userService.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto userdto){
		
		User user = userdto.transformToUser();
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
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value =  "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		
		return ResponseEntity.status(HttpStatus.OK).body(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody UserLoginDto user){
		
		User logged = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(logged);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(
			@PathVariable(name = "id") Long ownerId,
			@RequestParam(value = "size", defaultValue = "0") int size,
			@RequestParam(value = "page", defaultValue = "10") int page){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = RequestService.listAllByOwnerIdOnLazyModel(ownerId, pr);
		
		return ResponseEntity.status(HttpStatus.OK).body(pm);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateRole(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateRoleDto userDto){
		
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
		
	}

}
