package com.techland.paypay.mesh.serviceTypes;

import com.techland.paypay.mesh.contracts.ServiceType;
import com.techland.paypay.mesh.helper.Constants;

 final class PostServiceWithReturn implements ServiceType {

	@Override
	public String getRequestType() {
		return Constants.POST;
	}

	@Override
	public boolean getReturnType() {
		return true;
	}

}
