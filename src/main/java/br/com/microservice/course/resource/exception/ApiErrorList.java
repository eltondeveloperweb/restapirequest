package br.com.microservice.course.resource.exception;

import java.util.Date;
import java.util.List;

public class ApiErrorList extends ApiError{

	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public ApiErrorList(int code, String msg, Date date, List<String> errors) {
		super(code, msg, date);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
