package com.techland.paypay.serviceTypes;

import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.helper.Constants;

final class GetServiceWithReturn implements ServiceType {

	@Override
	public String getRequestType() {
		return Constants.GET;
	}

	@Override
	public boolean getReturnType() {
		return true;
	}

}
