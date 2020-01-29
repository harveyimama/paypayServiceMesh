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

public final class CreateUserService implements Service<User>{
    private final  ServiceType serviceType ;
    private final String URL;
    private final  boolean isForm;
	
	public CreateUserService()
	{	
	  this.serviceType =  ServiceTypeFactory.getInstance(ServiceTypeEnum.POSTWITHNORETURN);
	  this.URL = URLs.CU;
	  this.isForm = true;
	}

	@Override
	public ServiceResponse doRequest(final User user) {
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
	
	

}
