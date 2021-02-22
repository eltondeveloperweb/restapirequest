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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.model.PageModel;
import br.com.microservice.course.model.PageRequestModel;
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
	public ResponseEntity<PageModel<Request>> listAll(
			@RequestParam(value = "page") int page,
			@RequestParam(value =  "size") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(pr);
		
		return ResponseEntity.status(HttpStatus.OK).body(pm);
	}
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStageById(
			@PathVariable("id") Long id,
			@RequestParam("page") int page,
			@RequestParam("size") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);
		
		return ResponseEntity.status(HttpStatus.OK).body(pm);
		
	}

}
