package com.techland.paypay.contracts;

public abstract class ServiceResponse {

	private final String message;
	private final int responseCode;
	private final boolean success;

	public ServiceResponse(final String message, final int responseCode, final boolean success) {
		this.message = message;
		this.responseCode = responseCode;
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
