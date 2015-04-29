package com.tartner.dancehours.web.login;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class CreateAccountForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

	@NotBlank(message = CreateAccountForm.NOT_BLANK_MESSAGE)
	private String firstName;

	@NotBlank(message = CreateAccountForm.NOT_BLANK_MESSAGE)
	private String lastName;

    @NotBlank(message = CreateAccountForm.NOT_BLANK_MESSAGE)
	@Email(message = CreateAccountForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = CreateAccountForm.NOT_BLANK_MESSAGE)
	private String password;

	public String getFirstName() { return firstName; }
	public void setFirstName( final String firstName ) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName( final String lastName ) { this.lastName = lastName; }
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

	public void createAccount() {
	}
}
