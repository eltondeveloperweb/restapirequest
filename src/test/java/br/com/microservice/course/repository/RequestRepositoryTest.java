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
	
	public void saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Notebook Dell", "Para trabalhar em casa", new Date(), RequestState.OPEN, owner, null);
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	
	public void updateTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(1L, "Notebook Dell Branco", "Para trabalhar em casa", null, RequestState.OPEN, owner, null);
		Request updatedRequest = requestRepository.save(request);
		
		assertThat(updatedRequest.getDescription()).isEqualTo("Para trabalhar em casa");
		
	}
	
	public void getByIdTest() {
		
		Optional<Request> result = requestRepository.findById(1L);
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Notebook Dell");
		
	}
	
	public void listAllTest() {
		
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);
		
	}
	
	public void listByOwnerIdTest() {
		
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(1);
		
	}
	
	public void updateRequestStatusTest() {
		
		int affectedRows = requestRepository.updateStatus(4L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
		
	}

}
