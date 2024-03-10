package com.example.studsystem.dto;

public class JwtResponseDTO {
	private String roleName;
    private String token;

	public JwtResponseDTO(String roleName,String token) {
		this.roleName = roleName;
		this.token = token;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
    
    
}