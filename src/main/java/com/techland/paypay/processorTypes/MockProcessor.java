package com.techland.paypay.processorTypes;

import com.techland.paypay.contracts.ProcessorType;

public final class MockProcessor implements ProcessorType {

	private String url, data, contentType, authorization, requestType;
	private boolean isFormParam;

	public MockProcessor() {
	}

	public void setProcessorValues(String url, String data, String contentType, String authorization, boolean isFormParam,
			String requestType) {
		this.url = url;
		this.data = data;
		this.contentType = contentType;
		this.authorization = authorization;
		this.requestType = requestType;
		this.isFormParam = isFormParam;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T doProcessing(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam, final String requestType) {
		return (T) "Processed succesfully";
	}

	@Override
	public void run() {

		doProcessing(this.url, this.data, this.contentType, this.authorization, this.isFormParam, this.requestType);

	}

}
