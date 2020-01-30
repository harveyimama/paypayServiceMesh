package com.techland.paypay.contracts;

public interface Service<T> {

	ServiceResponse doRequest();

	ServiceType getServiceType();

	String getURL();

	boolean isFormParam();
	
	String getSuthorization();
	
	String getContentType();
	
	void addData(final T data);
	
	
}
