package com.techland.paypay.mesh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.impl.Login;
import com.techland.paypay.mesh.impl.PayPayController;
import com.techland.paypay.mesh.impl.User;
import com.techland.paypay.mesh.responses.AsyncResponse;
import com.techland.paypay.mesh.responses.LoginResponse;
import com.techland.paypay.mesh.responses.SimpleResponse;
import com.techland.paypay.mesh.services.CreateUserService;
import com.techland.paypay.mesh.services.GetUserService;
import com.techland.paypay.mesh.services.LoginService;


@SpringBootTest
class PaypayServiceMeshApplicationTests {
	
	PayPayController controller = new PayPayController();
	
	@Autowired
	User user ;	
	@Autowired
	Login login ;
	@Autowired
	AsyncResponse res ;
	@Autowired
	LoginResponse resLogin ;
	@Autowired
	SimpleResponse repSimple;
	@Autowired
	CreateUserService cus;
	@Autowired
	GetUserService gus;
	
	@Autowired
	LoginService ls;
	
	
	@BeforeEach
	 void init()
	{
		Settings.setTest();
		
		user.setUsername("Test");
		user.setFullname("Test");
		user.setPassword("Test");
		user.setEmail("Test");
		user.setMerchantId("Test");
		user.setRole("test");
		
		login.setUsername("Test");
		login.setPassword("Test");	
	}
	

	@Test
	void TestCreateUser() {
		
		cus.addData(user);
		res = cus.doRequest();
		
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println(res.getMessage());
		
		assert(res.getMessage().equals("Sucessfully sent"));
		assert(res.getSuccess());
		assert(res.getResponseCode()==0);	
		
			
		}
	
	@Test
	void TestLogin() {
		
		ls.addData(login);
		resLogin =	ls.doRequest();		
		assert(resLogin.getMessage().equals("Test sucessful"));
		assert(resLogin.getSuccess());
		assert(resLogin.getResponseCode()==0);	
		
		
		}
	
	
	
	@Test
	void TestGetUser() {
		gus.addData("");
		repSimple = 	(SimpleResponse) gus.doRequest()	;		
		assert(repSimple.getMessage().equals("Test sucessful"));
		assert(repSimple.getSuccess());
		assert(repSimple.getResponseCode()==0);	
		
		
		}
	
	
	
	
	@AfterEach
	protected void finalize()
	{
		Settings.setProd();
	}

}
