package br.com.microservice.course.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.enums.RequestState;
import br.com.microservice.course.repository.RequestRepository;

@Service
public class RequestService {
	
	private RequestRepository requestRepository;

	@Autowired
	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}
	
	public Request save(Request request) {
		
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createRequest = requestRepository.save(request);
		
		return createRequest;
		
	}
	
	public Request update(Request request) {
		Request updateRequest = requestRepository.save(request);
		return updateRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}
	
	public List<Request> listAll(){
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public List<Request> listAllByOwnerId(Long id){
		List<Request> requests = requestRepository.findAllByOwnerId(id);
		return requests;
	}

}
