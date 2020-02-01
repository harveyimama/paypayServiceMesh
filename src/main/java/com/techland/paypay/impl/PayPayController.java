package com.techland.paypay.impl;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.requests.LoginRequest;
import com.techland.paypay.responses.LoginResponse;
import com.techland.paypay.services.CreateUserResultService;
import com.techland.paypay.services.CreateUserService;

@RestController
public class PayPayController {

	@PostMapping
	public ServiceResponse CreateUser (final User user )
	{
		Service<User> u = new CreateUserService();		
		u.addData(user);		
	    u.doRequest();
	    
	    TimeUnit.SECONDS.sleep(2);
	    
	    Service<String> ur = new CreateUserResultService();
	    ur.addData("get id from first service");
	  ServiceResponse resp = ur.doRequest();
	    
	    if(resp.getSuccess())
	    while()
	    {
	    	 TimeUnit.SECONDS.sleep(2);
	    	 ur.doRequest();
	    }

	}

}
