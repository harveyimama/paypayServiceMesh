package com.techland.paypay.mesh.impl;

import org.springframework.stereotype.Service;

import com.techland.paypay.mesh.contracts.ServiceRequest;

@Service
public class User  implements ServiceRequest {
	
	private String id;
	private String username;
	private String password;
	private String email;
	private String fullname;
	private String role;
	private String merchantId;
	private boolean async =false;
	
	
	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(final String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(final String role) {
		this.role = role;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(final String merchantId) {
		this.merchantId = merchantId;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \" username\":\"" + username + "\",\" password\":\"" + password + "\",\" email\":\""
				+ email + "\",\" fullname\":\"" + fullname + "\",\" role\":\"" + role + "\",\" merchantId\":\"" + merchantId
				+ "\",\" async\":\"" + async + "\"}";
	}
	
	@Override
	public boolean isNull() {
		if(this.username ==null || this.fullname ==null || this.role == null || this.merchantId == null || this.email ==null )
		return true;
		else
			return false;
	}
	
	
	@Override
	public boolean isAsync() {
		return this.async;
	}
	@Override
	public void setAsync(final boolean async) {
		this.async = async;
	}
	

}
