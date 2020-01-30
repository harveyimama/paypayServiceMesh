package com.techland.paypay.services;

import com.techland.paypay.contracts.Service;
import com.techland.paypay.contracts.ServiceResponse;
import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.ennums.ServiceTypeEnum;
import com.techland.paypay.helper.URLs;
import com.techland.paypay.impl.User;
import com.techland.paypay.requests.LoginRequest;
import com.techland.paypay.responses.LoginResponse;
import com.techland.paypay.serviceTypes.ServiceTypeFactory;

public final class CreateUserService implements Service<User> {
	private final ServiceType serviceType;
	private final String URL, authorization;
	private String data;
	private final String contentType;
	private final boolean isForm;

	public CreateUserService() {
		this.serviceType = ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHNORETURN);
		this.URL = URLs.CU;
		this.isForm = true;
		this.authorization = "";
		this.contentType = "application/json";
		
	}

	@Override
	public ServiceResponse doRequest() {
		// TODO call PayPayProcesor
		return null;
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
	public String getSuthorization() {
		return authorization;
	}

	@Override
	public String getContentType() {

		return contentType;
	}

	@Override
	public void addData(User data) {
		this.data =data.toString();
		
	}

	
}
