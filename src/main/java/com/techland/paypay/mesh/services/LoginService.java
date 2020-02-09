package com.techland.paypay.mesh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.contracts.ProcessorType;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.contracts.ServiceType;
import com.techland.paypay.mesh.ennums.ServiceTypeEnum;
import com.techland.paypay.mesh.helper.URLs;
import com.techland.paypay.mesh.impl.Login;
import com.techland.paypay.mesh.processorTypes.GeneralProcessor;
import com.techland.paypay.mesh.responses.LoginResponse;
import com.techland.paypay.mesh.serviceTypes.ServiceTypeFactory;

@Component
public final class LoginService implements Service<Login> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	@Autowired
	private Login data;
	private final String contentType,name;
	private final boolean isForm;
	@Autowired
	private PayPayServiceProcessor<LoginService, Login,ProcessorType> serviceProcessor;
	@Autowired
	private GeneralProcessor processor;
	private int readTimeOut,connectTimeOut;
	@Autowired
	private LoginResponse loginResponse;
	

	public LoginService() {
		this.serviceType = ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHRETURN);
		this.URL = URLs.LO;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		this.readTimeOut = 2211;
		this.connectTimeOut = 221;
		this.name = "LoginService";
		
	}

	@Override
	public LoginResponse doRequest() {	
		
		ServiceResponse res = 	serviceProcessor.processService(this, this.data,this.processor);
		
		loginResponse.setAll(res.getMessage(), res.getResponseCode(), res.getSuccess());
		if(res.getSuccess())
		{
			//TODO add other things from result
			
		}
		return loginResponse;
	}

	@Override
	public ServiceType getServiceType() {
		return serviceType;
	}

	@Override
	public String getURL() {
		return URL;
	}

	@Override
	public boolean isFormParam() {
		return isForm;
	}

	@Override
	public String getAuthorization() {
		return authorization;
	}

	@Override
	public String getContentType() {

		return contentType;
	}

	@Override
	public void addData(Login data) {
		this.data = data;
		
	}

	@Override
	public int getReadTimeOut() {
		return this.readTimeOut;
	}

	@Override
	public int getConnectTimeOut() {
		return this.connectTimeOut;
	}

	@Override
	public String getName() {
		return this.name;
	}

	

}
