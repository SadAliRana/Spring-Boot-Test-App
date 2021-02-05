package com.test.app.ws.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	
	@NotNull(message="First name cannot be null")
	@Size(min=2,message="First name cannot be less than 2 characters")
	private String firstname;
	
	@NotNull(message="Last name cannot be null")
	private String lastname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
