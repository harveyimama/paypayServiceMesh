package com.techland.paypay.mesh.responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.impl.User;


@Component
public class LoginResponse extends ServiceResponse {

	private User user;
	
	
	public LoginResponse()
	{
		super();
	}
		
	public void setUser(User user) {
		this.user = user;
	}

	User getUser()
	{
		return this.user;
	}
	
	
	
	
	
 
	
}
