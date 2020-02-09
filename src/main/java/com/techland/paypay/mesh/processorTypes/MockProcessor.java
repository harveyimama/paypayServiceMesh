package com.techland.paypay.mesh.processorTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.techland.paypay.mesh.contracts.ProcessorType;
import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.responses.ResponseFactory;
import com.techland.paypay.mesh.responses.SimpleResponse;
@Component
public final class MockProcessor implements ProcessorType {

	private String url, data, contentType, authorization, requestType;
	private boolean isFormParam;
	private int connectTimeOut,readTimeOut; 
	@Autowired
	private SimpleResponse response;

	public MockProcessor() {
	}

	public void setProcessorValues(String url, String data, String contentType, String authorization, boolean isFormParam,
			String requestType,int connectTimeOut,int readTimeOut) {
		this.url = url;
		this.data = data;
		this.contentType = contentType;
		this.authorization = authorization;
		this.requestType = requestType;
		this.isFormParam = isFormParam;
		this.connectTimeOut = connectTimeOut;
		this.readTimeOut = readTimeOut;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T doProcessing(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam, final String requestType,final int connectTimeOut,final int readTimeOut ) {
			
			try {
				
				return  (T)  ResponseFactory.MockResponse(response);
				
			} catch (Exception e) {
				
				return (T) errorResponse() ;
			}
	}

	@Override
	public void run() {

		doProcessing(this.url, this.data, this.contentType, this.authorization, this.isFormParam, this.requestType,this.connectTimeOut,this.readTimeOut);

	}

	@Override
	public ServiceResponse errorResponse() {
		return ResponseFactory.HTTPResponse(response);
	}

}
