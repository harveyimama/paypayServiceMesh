package com.techland.paypay.mesh.responses;

import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.impl.User;

@Component
public class UserResponse extends ServiceResponse {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
