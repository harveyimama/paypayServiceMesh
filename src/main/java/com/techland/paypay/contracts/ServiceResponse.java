package com.techland.paypay.contracts;

public abstract class ServiceResponse {

	private String message;
	private int responseCode;
	private boolean success;

	public ServiceResponse() {
	}

	public void setAll(String message,int responseCode,boolean success) {
		this.message = message;
		this.responseCode = responseCode;
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public boolean getSuccess() {
		return this.success;
	}

}
