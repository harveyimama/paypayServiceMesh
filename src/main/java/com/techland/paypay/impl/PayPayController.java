package com.techland.paypay.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.requests.LoginRequest;
import com.techland.paypay.responses.LoginResponse;
import com.techland.paypay.services.CreateUserService;

@RestController
public class PayPayController {

	@PostMapping
	public ServiceResponse CreateUser (final User user )
	{
		return new CreateUserService().doRequest(user);		
	}

}
