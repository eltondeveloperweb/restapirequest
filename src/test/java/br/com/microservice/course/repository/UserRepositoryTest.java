package br.com.microservice.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.microservice.course.domain.User;
import br.com.microservice.course.domain.enums.Role;


@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		
		User user = new User(null, "carmelita", "carmelita@gmail.com", "123", Role.ADMINSTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void updateTest() {
		
		User user = new User(1L, "carmelita silva", "carmelita.silva@gmail.com", "123", Role.ADMINSTRATOR, null, null);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("carmelita silva");
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<User> result = userRepository.findById(6L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("123");
		
	}
	
	@Test
	public void listTest() {
		
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1L);
		
	}
	
	@Test
	public void loginTest() {
		
		Optional<User> result = userRepository.login("carmelita.silva@gmail.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void updateRoleTest() {
		int affectedRows = userRepository.updateRole(1L, Role.ADMINSTRATOR);
		assertThat(affectedRows).isEqualTo(1);
	}

}
