package com.techland.paypay.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.responses.*;
import com.techland.paypay.services.CreateUserResultService;
import com.techland.paypay.services.CreateUserService;
import com.techland.paypay.services.LoginService;

@RestController
public class PayPayController {

	// TODO comments,tests,build request pojos

	@PostMapping("/api/async/user")
	public AsyncResponse CreateUserAsync(final User user) {
		AsyncResponse resp = null;

		if (user == null)
			return SystemResponse.NotNull(resp);

		Service<User> u = new CreateUserService();
		u.addData(user);

		return (AsyncResponse) u.doRequest();

	}
	
	
	@PostMapping("/api/async/user/{id}")
	public ServiceResponse getUser(final String id) {
		
		ServiceResponse resp = null;
		if (id == null || "".equals(id))
			return  SystemResponse.NotNull(resp);
		
			Service<String> ur = new CreateUserResultService();
			ur.addData(id);

		    resp = ur.doRequest();
			
			if(resp==null)
				return  SystemResponse.NullResponse(resp);
			else
				return resp;	

	}
	

	@PostMapping("/api/user")
	public ServiceResponse CreateUser(final User user) {
		ServiceResponse resp = null;
		if (user == null)
			return SystemResponse.NotNull(resp);

		try {
			Service<User> u = new CreateUserService();
			u.addData(user);
			AsyncResponse asyncresp = (AsyncResponse) u.doRequest();

			TimeUnit.SECONDS.sleep(2);

			Service<String> ur = new CreateUserResultService();
			ur.addData(asyncresp.getID().toString());

			resp = ur.doRequest();
			resp = iterateRequest(resp, ur);

			if (resp == null)
				return SystemResponse.NullResponse(resp);
			else
				return resp;

		} catch (InterruptedException e) {
			e.printStackTrace();
			return SystemResponse.TryCatch(resp);
		}

	}

	@PostMapping("/api/login")
	public LoginResponse Login(final Login login) {
		LoginResponse resp = null;

		if (login == null)
			return SystemResponse.NotNull(resp);

		Service<Login> u = new LoginService();
		u.addData(login);
		resp = (LoginResponse) u.doRequest();

		if (resp == null)
			return SystemResponse.NullResponse(resp);
		else
			return resp;

	}

	@SuppressWarnings({ "unchecked" })
	private <R extends Service<S>, T extends ServiceResponse, S> T iterateRequest(T request, R service) {
		int i = 0;
		while (request != null && !request.getSuccess() && i < 2) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			request = (T) service.doRequest();
			i = i++;
		}

		return request;
	}

}
