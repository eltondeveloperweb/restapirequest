package br.com.microservice.course.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.domain.enums.RequestState;

public class RequestUpdateDto {
	
	@NotBlank(message = "Subject is required")
	private String subject;
	private String description;
	
	@NotNull(message = "State is required")
	private RequestState state;
	
	@NotNull(message = "Owner is required")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public Request transformToRequest() {
		
		Request request = new Request(null, this.subject, this.description, null, this.state, this.owner, stages);	
		return request;
	}

	public RequestUpdateDto() {
	}

	public RequestUpdateDto(@NotBlank(message = "Subject is required") String subject, String description,
			@NotNull(message = "Owner is required") User owner, List<RequestStage> stages) {
		
		this.subject = subject;
		this.description = description;
		this.owner = owner;
		this.stages = stages;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<RequestStage> getStages() {
		return stages;
	}

	public void setStages(List<RequestStage> stages) {
		this.stages = stages;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}
	
}
