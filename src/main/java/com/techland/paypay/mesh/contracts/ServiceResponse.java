package com.techland.paypay.mesh.contracts;

public abstract class ServiceResponse {

	private String message;
	private int responseCode;
	private boolean success;

	public ServiceResponse() {
	}

	public void setAll(final String message,final int responseCode,final boolean success) {
		this.message = message;
		this.responseCode = responseCode;
		this.success = success;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setResponseCode(final int responseCode) {
		this.responseCode = responseCode;
	}

	public void setSuccess(final boolean success) {
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
