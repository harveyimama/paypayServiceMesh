package com.techland.paypay.mesh.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.mesh.config.PayPayLogger;
import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.responses.AsyncResponse;
import com.techland.paypay.mesh.responses.LoginResponse;
import com.techland.paypay.mesh.responses.ResponseFactory;
import com.techland.paypay.mesh.services.CreateUserResultService;
import com.techland.paypay.mesh.services.CreateUserService;
import com.techland.paypay.mesh.services.LoginService;

/**
 * @author Harvey Imama Recieves and routes requests
 *
 */

@RestController
public class PayPayController {

	// TODO tests

	// merchant,user (tied to merchant) ,merchantwallet,card (customer),
	// customer,transaction,invoice,report,product,integration

	/**
	 * @param user
	 * @return
	 */
	@Autowired
	AsyncResponse respAsync;
	@Autowired
	LoginResponse respLogin;
	@Autowired
	CreateUserService cus;
	@Autowired
	PayPayLogger<Service<User>, User> userLogger;

	@PostMapping("/api/async/user")
	public AsyncResponse CreateUserAsync(final User user) {

		if (user.isNull())
		{
			userLogger.doLog(cus, user.toString());
			return ResponseFactory.NotNull(respAsync);		
		}
		
		cus.addData(user);
		respAsync = (AsyncResponse) cus.doRequest();

		userLogger.doLog(cus, user.toString(), respAsync.getMessage(), respAsync.getID().toString());

		return respAsync;

	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/api/async/user/{id}")
	public ServiceResponse getUser(final String id) {

		ServiceResponse resp = null;
		if (id == null || "".equals(id))
			return ResponseFactory.NotNull(resp);

		Service<String> ur = new CreateUserResultService();
		ur.addData(id);
		resp = ur.doRequest();

		if (resp == null)
			resp = ResponseFactory.NullResponse(resp);

		new PayPayLogger<Service<String>, String>().doLog(ur, id, resp.getMessage());

		return resp;

	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/api/user")
	public ServiceResponse CreateUser(final User user) {
		ServiceResponse resp = null;
		Service<User> u = null;

		if (user == null)
			return ResponseFactory.NotNull(respAsync);

		try {

			u = new CreateUserService();
			u.addData(user);
			respAsync = (AsyncResponse) u.doRequest();

			TimeUnit.SECONDS.sleep(2);

			Service<String> ur = new CreateUserResultService();
			ur.addData(respAsync.getID().toString());

			resp = ur.doRequest();
			resp = iterateRequest(resp, ur);

			if (resp == null)
				resp = ResponseFactory.NullResponse(resp);

		} catch (InterruptedException e) {
			e.printStackTrace();
			resp = ResponseFactory.TryCatch(resp);
		}

		new PayPayLogger<Service<User>, User>().doLog(u, user.toString(), respAsync.getID().toString(),
				resp.getMessage());
		return resp;
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/api/login")
	public LoginResponse Login(final Login login) {
		if (login == null)
			return ResponseFactory.NotNull(respLogin);

		Service<Login> u = new LoginService();
		u.addData(login);
		respLogin = (LoginResponse) u.doRequest();

		if (respLogin == null)
			respLogin = ResponseFactory.NullResponse(respLogin);
		
		new PayPayLogger<Service<Login>, Login>().doLog(u, login.toString(),respLogin.getMessage());
			return respLogin;

	}

	/**
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private <R extends Service<S>, T extends ServiceResponse, S> T iterateRequest(T request, R service) {
		int i = 0;
		while (request != null && !request.getSuccess() && i < Settings.ASYNC_WAIT_TIME) {
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
