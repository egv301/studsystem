package com.example.studsystem.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationUserDTO {
	
	@NotBlank(message = "Username cannot be empty")
    private String username;
	@NotBlank(message = "Password cannot be empty")
    private String password;
	@NotBlank(message = "Confirm password cannot be empty")
    private String confirmPassword;
	@NotBlank(message = "Email cannot be empty")
    private String email;
    
    public RegistrationUserDTO() {}
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RegistrationUserDTO [username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + "]";
	}
    
    
}