package com.techland.paypay.services;

import com.techland.paypay.contracts.ProcessorType;
import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.ennums.ServiceTypeEnum;
import com.techland.paypay.helper.URLs;
import com.techland.paypay.impl.Login;
import com.techland.paypay.processorTypes.GeneralProcessor;
import com.techland.paypay.responses.LoginResponse;
import com.techland.paypay.serviceTypes.ServiceTypeFactory;

public final class LoginService implements Service<Login> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	private Login data;
	private final String contentType;
	private final boolean isForm;
	private PayPayServiceProcessor<LoginService, Login,ProcessorType> serviceProcessor;
	private GeneralProcessor processor;
	private int readTimeOut,connectTimeOut;
	

	public LoginService() {
		this.serviceType = ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHRETURN);
		this.URL = URLs.LO;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		this.readTimeOut = 2211;
		this.connectTimeOut = 221;

	}

	@Override
	public LoginResponse doRequest() {		
		return 	serviceProcessor.processService(this, this.data,this.processor);
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

	

}
