package com.techland.paypay.mesh.contracts;


public interface ProcessorType extends Runnable {

	<T> T doProcessing(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam,String requestType,int connectTimeOut,int readTimeOut);
	
	void setProcessorValues(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam,String requestType,int connectTimeOut,int readTimeOut);
	
	@Override
	public void run();
	
	ServiceResponse errorResponse();

}
