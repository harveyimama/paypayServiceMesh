package com.techland.paypay.mesh.services;



import org.springframework.beans.factory.annotation.Autowired;

import com.techland.paypay.mesh.contracts.ProcessorType;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.contracts.ServiceType;
import com.techland.paypay.mesh.ennums.ServiceTypeEnum;
import com.techland.paypay.mesh.helper.URLs;
import com.techland.paypay.mesh.impl.User;
import com.techland.paypay.mesh.processorTypes.GeneralProcessor;
import com.techland.paypay.mesh.serviceTypes.ServiceTypeFactory;

public final class CreateUserResultService implements Service<String> {

	private final ServiceType serviceType;
	private final String URL, authorization;
	private String id;
	@Autowired
	private GeneralProcessor processor;
	private final String contentType,name;
	private final boolean isForm;
	@Autowired
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
		this.name ="CreateUserResultService";
		this.serviceProcessor = new PayPayServiceProcessor<CreateUserResultService, String, ProcessorType>();
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

	@Override
	public String getName() {
		return this.name;
	}

}
