package com.techland.paypay.services;

import java.util.Optional;

import com.techland.paypay.contracts.ProcessorType;
import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.ennums.ServiceTypeEnum;
import com.techland.paypay.helper.URLs;
import com.techland.paypay.impl.User;
import com.techland.paypay.processorTypes.GeneralProcessor;
import com.techland.paypay.requests.LoginRequest;
import com.techland.paypay.responses.LoginResponse;
import com.techland.paypay.serviceTypes.ServiceTypeFactory;

public final class CreateUserResultService implements Service<String> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	private String id;
	private GeneralProcessor processor;
	private final String contentType;
	private final boolean isForm;
	private PayPayServiceProcessor<CreateUserResultService, String,ProcessorType> serviceProcessor;
	private int connectTimeOut,readTimeOut;

	public CreateUserResultService() {
		this.serviceType = ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHRETURN);
		this.URL = URLs.CUR;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		this.connectTimeOut = 1221;
		this.readTimeOut =1221;

	}

	@Override
	public ServiceResponse doRequest() {		
	
		return 	serviceProcessor.processService(this, this.id,this.processor);
		
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
	public void addData(String id) {
		this.id = id;

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
