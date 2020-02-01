package com.techland.paypay.contracts;

public interface ProcessorType extends Runnable {

	<T> T doProcessing(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam,String requestType);
	
	void setProcessorValues(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam,String requestType);
	
	@Override
	public void run();

}
