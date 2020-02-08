package com.techland.paypay.mesh.impl;

import org.springframework.stereotype.Service;

import com.techland.paypay.mesh.contracts.ServiceRequest;

@Service
public class Login implements ServiceRequest {
	
	private String username;
	private String password;
	
	
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
	@Override
	public String toString() {
		return "{\"username\":\"" + username + "\", password\":\"" + password + "}";
	}
	@Override
	public boolean isNull() {
		if(this.username == null || this.password == null)
		return true;
		else
			return false;
	}
	
	

}
