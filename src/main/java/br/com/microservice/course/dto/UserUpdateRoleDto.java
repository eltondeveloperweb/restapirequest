package br.com.microservice.course.dto;

import javax.validation.constraints.NotNull;

import br.com.microservice.course.domain.enums.Role;

public class UserUpdateRoleDto {
	
	@NotNull(message = "Role is required")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
