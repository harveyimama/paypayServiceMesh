package com.techland.paypay.serviceTypes;

import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.helper.Constants;

 final class PostServiceWithNoReturn implements ServiceType{

	@Override
	public String getRequestType() {
		return Constants.POST;
	}

	@Override
	public boolean getReturnType() {
		return false;
	}

	
}
