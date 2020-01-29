package com.techland.paypay.responses;

import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.impl.User;

public class LoginResponse extends ServiceResponse {

	private final  User user;
	
	public LoginResponse(String message, int responseCode, boolean success,User user) {
		super(message, responseCode, success);  
		this.user = user;
	}

		
	User getUser()
	{
		return this.user;
	}
	
	
	
	
	
 
	
}
