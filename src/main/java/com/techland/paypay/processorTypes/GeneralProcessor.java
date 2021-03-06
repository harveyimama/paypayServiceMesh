package com.techland.paypay.processorTypes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


import com.techland.paypay.config.PayPayHTTPConnection;
import com.techland.paypay.contracts.ProcessorType;

public final class GeneralProcessor implements ProcessorType {

	private String url, data, contentType, authorization, requestType;
	private boolean isFormParam;
	private int connectTimeOut,readTimeOut; 

	public GeneralProcessor() {
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
		
		String finalurl = "", serviceOutPut = "";
	
		if(!isFormParam)
			finalurl = url.concat(data);
		else
			finalurl = url;	
		
		try {
			
			HttpURLConnection connection = PayPayHTTPConnection.getInstance(finalurl);
			connection.setDoOutput(true);
			connection.setRequestMethod(requestType);
			connection.setRequestProperty("Content-Type",contentType);
			connection.setRequestProperty("Authorization",authorization);
			if(connectTimeOut > 0)
			connection.setConnectTimeout(connectTimeOut);
			if(readTimeOut > 0)
			connection.setReadTimeout(readTimeOut);
			
			if(isFormParam)
			{
			OutputStream os = connection.getOutputStream();
			os.write(data.getBytes());
			os.flush();
			os.close();
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(connection.getInputStream())));

			String thirdPartyOutput = "";

			while ((thirdPartyOutput = br.readLine()) != null) {

				serviceOutPut = thirdPartyOutput;
			}
			
			br.close();
			connection.disconnect();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
		return (T) serviceOutPut;
	}
	
	

	@Override
	public void run() {

		doProcessing(this.url, this.data, this.contentType, this.authorization, this.isFormParam, this.requestType,this.connectTimeOut,this.readTimeOut);

	}
	
	
}
