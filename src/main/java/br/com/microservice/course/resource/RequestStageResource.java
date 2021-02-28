package br.com.microservice.course.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.dto.RequestStageSaveDto;
import br.com.microservice.course.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {
	
	private RequestStageService requestStageService;

	@Autowired
	public RequestStageResource(RequestStageService requestStageService) {
		this.requestStageService = requestStageService;
	}
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDto requestStageDto){
		
		RequestStage requestStage = requestStageDto.transformToRequestStage();
		RequestStage createStage = requestStageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable("id") Long id){
		
		RequestStage stage = requestStageService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(stage);
	}

}
