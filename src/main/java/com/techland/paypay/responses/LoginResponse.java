package com.techland.paypay.responses;

import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.impl.User;

public class LoginResponse extends ServiceResponse {

	private   User user;
		
	public void setUser(User user) {
		this.user = user;
	}

	User getUser()
	{
		return this.user;
	}
	
	
	
	
	
 
	
}
