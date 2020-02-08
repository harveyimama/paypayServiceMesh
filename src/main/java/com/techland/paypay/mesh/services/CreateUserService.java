package com.techland.paypay.mesh.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.contracts.ProcessorType;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.contracts.ServiceType;
import com.techland.paypay.mesh.ennums.ServiceTypeEnum;
import com.techland.paypay.mesh.helper.URLs;
import com.techland.paypay.mesh.impl.User;
import com.techland.paypay.mesh.processorTypes.GeneralProcessor;
import com.techland.paypay.mesh.responses.AsyncResponse;
import com.techland.paypay.mesh.serviceTypes.ServiceTypeFactory;

@Component
public final  class CreateUserService implements Service<User> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	@Autowired
	public User data;
	@Autowired
	public GeneralProcessor processor;
	private final String contentType,name;
	private final boolean isForm;
	private int connectTimeOut, readTimeOut;
	@Autowired
	public PayPayServiceProcessor<CreateUserService, User, ProcessorType> serviceProcessor;
	@Autowired
	private AsyncResponse serviceResponse;

	public CreateUserService() {
		
		this.serviceType =  ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHNORETURN);
		this.URL = URLs.CU;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		this.connectTimeOut = 0;
		this.readTimeOut = 0;
			this.name = "CreateUserService";
	}

	@Override
	public AsyncResponse doRequest() {
		
		this.serviceResponse.setID(UUID.randomUUID().toString()); 
		serviceProcessor.processService(this, this.data, this.processor);
		serviceResponse.setAll("Sucessfully sent", 0, true);
		return serviceResponse;
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

	@Override
	public String getName() {
		return this.name;
	}

}
