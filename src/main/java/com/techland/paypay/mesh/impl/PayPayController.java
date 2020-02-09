package com.techland.paypay.mesh.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.mesh.config.PayPayLogger;
import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.responses.CreateResponse;
import com.techland.paypay.mesh.responses.LoginResponse;
import com.techland.paypay.mesh.responses.ResponseFactory;
import com.techland.paypay.mesh.responses.UserResponse;
import com.techland.paypay.mesh.responses.AcknowledgementResponse;
import com.techland.paypay.mesh.services.GetUserService;
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

	@Autowired
	CreateResponse respCreate;
	@Autowired
	LoginResponse respLogin;
	@Autowired
	CreateUserService cus;
	@Autowired
	LoginService ls;
	@Autowired
	PayPayLogger paypayLogger;
	@Autowired
	AcknowledgementResponse respAck;
	@Autowired
	GetUserService gus;
	@Autowired
	UserResponse respUser;

	
	/**
	 * @param user
	 * @return
	 */
	@GetMapping(path = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse getUser(@PathVariable(value = "id") final String id) {

		if (id == null || "".equals(id)) {
			paypayLogger.doLog(cus.getName(), id);
			return ResponseFactory.NotNull(respUser);
		}

		gus.addData(id);
		respUser = (UserResponse) gus.doRequest();

		if (respUser == null)
			respUser = ResponseFactory.NullResponse(respUser);

		paypayLogger.doLog(gus.getName(), id, respAck.getMessage());

		return respUser;

	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse CreateUser(@RequestBody final User user) {

		if (user.isNull()) {
			paypayLogger.doLog(cus.getName(), user.toString());
			return ResponseFactory.NotNull(respAck);
		}

		cus.addData(user);
		respCreate = (CreateResponse) cus.doRequest();
		
		if(user.isAsync())
		{
		paypayLogger.doLog(cus.getName(), user.toString(), respCreate.getID().toString(), respCreate.getMessage());
		return respCreate;
		}
		
		gus.addData(respCreate.getID().toString());
		respUser = iterateRequest(respUser, gus);
		if (respUser == null)
			respUser = ResponseFactory.NullResponse(respUser);
		paypayLogger.doLog(cus.getName(), user.toString(), respCreate.getID().toString(), respUser.getMessage());
		
		return respCreate;
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse Login(@RequestBody final Login login) {
		if (login.isNull()) {
			paypayLogger.doLog(ls.getName(), login.toString());
			return ResponseFactory.NotNull(respLogin);
		}

		ls.addData(login);
		respLogin = (LoginResponse) ls.doRequest();

		if (respLogin == null)
			respLogin = ResponseFactory.NullResponse(respLogin);

		paypayLogger.doLog(ls.getName(), login.toString(), respLogin.getMessage());
		return respLogin;

	}

	/**
	 * @param user
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private <R extends Service<S>, T extends ServiceResponse, S> T iterateRequest(T request, R service) {

		for (int i = 0; i < Settings.ASYNC_NUMBER_OF_TRIES; i++) {

			request = (T) service.doRequest();
			if (request != null && !request.getSuccess()) {
				try {
					TimeUnit.SECONDS.sleep(Settings.ASYNC_WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
					request = ResponseFactory.TryCatch(request);
				}
			} else {
				if(request!=null)
				break;
			}

		}

		return request;
	}

}
