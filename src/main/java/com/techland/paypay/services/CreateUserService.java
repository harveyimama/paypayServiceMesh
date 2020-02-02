package com.techland.paypay.services;

import com.techland.paypay.contracts.ProcessorType;
import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.ennums.ServiceTypeEnum;
import com.techland.paypay.helper.URLs;
import com.techland.paypay.impl.User;
import com.techland.paypay.processorTypes.GeneralProcessor;
import com.techland.paypay.serviceTypes.ServiceTypeFactory;



public final class CreateUserService implements Service<User> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	private User data;
	private GeneralProcessor processor;
	private final String contentType;
	private final boolean isForm;
	private int connectTimeOut,readTimeOut;
	private PayPayServiceProcessor<CreateUserService, User,ProcessorType> serviceProcessor;

	public CreateUserService() {
		this.serviceType = ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHNORETURN);
		this.URL = URLs.CU;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		this.connectTimeOut = 0;
		this.readTimeOut =0;
		

	}

	@Override
	public ServiceResponse doRequest() {		
		serviceProcessor.processService(this, data,processor); 
		return new ServiceResponse("Sucessfully sent",0,true); 
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
	public void addData(User data) {
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
