package br.com.microservice.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.domain.enums.RequestState;

@SpringBootTest
public class RequestRepositoryTest {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Notebook Dell", "Para trabalhar em casa", new Date(), RequestState.OPEN, owner, null);
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(3L);
	}
	
	@Test
	public void updateTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(3L, "Notebook Dell Branco", "Para trabalhar em casa", null, RequestState.OPEN, owner, null);
		Request updatedRequest = requestRepository.save(request);
		
		assertThat(updatedRequest.getDescription()).isEqualTo("Para trabalhar em casa");
		
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<Request> result = requestRepository.findById(3L);
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Notebook Dell");
		
	}
	
	@Test
	public void listAllTest() {
		
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(3);
		
	}
	
	@Test
	public void listByOwnerIdTest() {
		
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(3);
		
	}
	
	@Test
	public void updateRequestStatusTest() {
		
		int affectedRows = requestRepository.updateStatus(4L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
		
	}

}