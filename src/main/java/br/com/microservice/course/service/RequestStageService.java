package br.com.microservice.course.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.domain.enums.RequestState;
import br.com.microservice.course.exception.NotFoundException;
import br.com.microservice.course.repository.RequestRepository;
import br.com.microservice.course.repository.RequestStageRepository;

@Service
public class RequestStageService {
	
	private RequestStageRepository requestStageRepository;
	private RequestRepository requestRepository;
	
	@Autowired
	public RequestStageService(RequestStageRepository requestStageRepository, 
		   RequestRepository requestRepository) {
		
		this.requestStageRepository = requestStageRepository;
		this.requestRepository = requestRepository;
	}

	public RequestStage save(RequestStage stage) {
		
		stage.setRealizationDate(new Date());
		RequestStage createdStage = requestStageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		
		return createdStage;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.orElseThrow(()-> new NotFoundException("There are not request stage with " + id));
	}
	
	public List<RequestStage> listAllByRequestId(Long requestId){
		
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
		
	}

}
