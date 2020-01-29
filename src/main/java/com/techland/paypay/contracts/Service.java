package com.techland.paypay.contracts;

public interface Service<T> {

	ServiceResponse doRequest(final T request);

	ServiceType getServiceType();

	String getURL();

	boolean isFormParam();
}
