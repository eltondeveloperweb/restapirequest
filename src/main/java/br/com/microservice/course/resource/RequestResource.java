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
import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.service.RequestService;
import br.com.microservice.course.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	
	private RequestService requestService;
	private RequestStageService stageService;

	@Autowired
	public RequestResource(RequestService requestService, RequestStageService stageService) {
		this.requestService = requestService;
		this.stageService = stageService;
	}
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request){
		
		Request createRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRequest);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody Request request){
		
		request.setId(id);
		
		Request updateRequest = requestService.update(request);
		return ResponseEntity.status(HttpStatus.OK).body(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable("id") Long id){
		
		Request request = requestService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(request);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> listAll(){
		
		List<Request> requests = requestService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(requests);
		
	}
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStageById(@PathVariable("id") Long id){
		
		List<RequestStage> stages = stageService.listAllByRequestId(id);
		return ResponseEntity.status(HttpStatus.OK).body(stages);
		
	}

}
