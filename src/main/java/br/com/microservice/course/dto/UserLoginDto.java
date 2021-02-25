package br.com.microservice.course.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginDto {
	
	@Email(message = "Invalid e-mail")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	public UserLoginDto() {
	}

	public UserLoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
