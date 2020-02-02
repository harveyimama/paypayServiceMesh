package com.techland.paypay.impl;


import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.annotations.TryCatch;
import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;

import com.techland.paypay.services.CreateUserResultService;
import com.techland.paypay.services.CreateUserService;

@RestController
@TryCatch 
public class PayPayController {
	
	//TODO annotation to check null, comments,tests

	@PostMapping("/api/user")
	public ServiceResponse CreateUser (final User user )
	{
		ServiceResponse resp = null;
		
		Service<User> u = new CreateUserService();		
		u.addData(user);		
	    u.doRequest();
	    
	    TimeUnit.SECONDS.sleep(2);
	    
	    Service<String> ur = new CreateUserResultService();
	    ur.addData("get id from first service");
	    resp = ur.doRequest();
	    int i = 0;
	    while(!resp.getSuccess() && i < 2)
	    {
	    	 TimeUnit.SECONDS.sleep(2);
	    	 resp = ur.doRequest();
	    	 i=i++;
	    }
    
	    return resp;

	}

}
