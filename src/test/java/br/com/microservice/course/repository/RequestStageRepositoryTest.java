package br.com.microservice.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.domain.enums.RequestState;

@SpringBootTest
public class RequestStageRepositoryTest {
	
	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Test
	public void saveTest() {
		
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request();
		request.setId(1L);
		
		RequestStage stage = new RequestStage(null, "Novo Notebook", new Date(), RequestState.CLOSED, request, owner);
		RequestStage createdStage = requestStageRepository.save(stage);
		
		assertThat(createdStage.getId()).isEqualTo(1L);
	}

	@Test
	public void getStageById() {
		
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stage = result.get();
		
		assertThat(stage.getDescription()).isEqualTo("Novo Notebook");
	}
	
	@Test
	public void listByRequestTest() {
		
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertThat(stages.size()).isEqualTo(1);
	}
}
