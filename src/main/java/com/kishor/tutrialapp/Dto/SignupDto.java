package com.kishor.tutrialapp.Dto;

public class SignupDto {

	private String name;
	private String username;
	private String email;
	private String password;
	
	public SignupDto() {
		// TODO Auto-generated constructor stub
	}

	public SignupDto(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "SignupDto [name=" + name + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}
	
	

	
}
