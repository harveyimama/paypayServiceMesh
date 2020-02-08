package com.techland.paypay.mesh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.impl.Login;
import com.techland.paypay.mesh.impl.PayPayController;
import com.techland.paypay.mesh.impl.User;
import com.techland.paypay.mesh.responses.AsyncResponse;
import com.techland.paypay.mesh.responses.ResponseFactory;


@SpringBootTest
class PaypayServiceMeshApplicationTests {
	
	PayPayController controller;
	
	@Autowired
	User user;	
	@Autowired
	Login login ;
	@Autowired
	AsyncResponse res ;
	
	
	@BeforeEach
	 void init()
	{
		Settings.setTest();
		
		user.setUsername("Test");
		user.setFullname("Test");
		user.setPassword("Test");
		login.setUsername("Test");
		login.setPassword("Test");	
	}
	

	@Test
	void TestCreateUser() {
		assert(res !=null);
		assert(user !=null);
	
		/*res = (AsyncResponse) controller.CreateUser(null);
		assert(res.equals(SystemResponse.NotNull(res)));
		
		res = (AsyncResponse) controller.CreateUser(user);
		assert(res.getMessage().equals("Test sucessful"));
		assert(res.getSuccess());
		assert(res.getResponseCode()==0);	*/
		
		}
	
	/*@Test
	void TestLogin() {
		
		res = controller.Login(null);
		assert(res.equals(SystemResponse.NotNull(new AsyncResponse())));
		
		res = controller.Login(login);
		assert(res.getMessage().equals("Test sucessful"));
		assert(res.getSuccess());
		assert(res.getResponseCode()==0);		
		
		}
	
	
	
	@Test
	void TestCreateUserAsync() {
		
		res = controller.CreateUserAsync(null);
		assert(res.equals(SystemResponse.NotNull(new AsyncResponse())));
		
		res = controller.CreateUserAsync(user);
		assert(res.getMessage().equals("Test sucessful"));
		assert(res.getSuccess());
		assert(res.getResponseCode()==0);	
		
		}
	
	
	
	@Test
	void TestGetUser() {
		
		res = controller.getUser(null);
		assert(res.equals(SystemResponse.NotNull(new AsyncResponse())));
		
		res = controller.getUser("");
		assert(res.getMessage().equals("Test sucessful"));
		assert(res.getSuccess());
		assert(res.getResponseCode()==0);	
		
		}
	
	*/
	
	
	@AfterEach
	protected void finalize()
	{
		Settings.setProd();
	}

}
