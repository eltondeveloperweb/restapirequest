package br.com.microservice.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.microservice.course.domain.User;
import io.restassured.RestAssured;


@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void getAllTest() {
		RestAssured.given().log().all()
			.when()
			.get("http://localhost:8082/users")
			.then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test
	public void getUserById() {
		RestAssured.given().log().all()
		.when()
		.get("http://localhost:8082/users/1")
		.then()
		.statusCode(200)
		.log()
		.all();
	}
	
	@Test
	public void saveUserTest() {
		RestAssured.given().log().all()
		.when()
		.post("http://localhost:8082/users")
		.then()
		.log()
		.all();
		
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.given().log().all()
		.when()
		.put("http://localhost:8082/users/1")
		.then()
		.log()
		.all();
	}
	
	@Test
	public void updateRoleTest() {
		RestAssured.given().log().all()
		.when()
		.patch("http://localhost:8082/users/1")
		.then()
		.log()
		.all();
	}
	
	public void loginTest() {
		
		Optional<User> result = userRepository.login("carmelita.silva@gmail.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
		
	}	

}
