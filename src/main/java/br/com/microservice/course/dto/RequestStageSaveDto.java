package br.com.microservice.course.dto;

import javax.validation.constraints.NotNull;

import br.com.microservice.course.domain.Request;
import br.com.microservice.course.domain.RequestStage;
import br.com.microservice.course.domain.User;
import br.com.microservice.course.domain.enums.RequestState;

public class RequestStageSaveDto {
	
	private String description;
	
	@NotNull(message = "State is required")
	private RequestState state;
	
	@NotNull(message = "Request is required")
	private Request request;
	
	@NotNull(message = "Owner is required")
	private User owner;

	public RequestStageSaveDto() {
	}

	public RequestStageSaveDto(String description, RequestState state,
			Request request,
			User owner) {
		this.description = description;
		this.state = state;
		this.request = request;
		this.owner = owner;
	}
	
	public RequestStage transformToRequestStage() {
		
		RequestStage stage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
		return stage;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
