package com.techland.paypay.responses;
import java.util.UUID;

import com.techland.paypay.contracts.ServiceResponse;



public class AsyncResponse extends ServiceResponse {
	
	private  UUID ID;

	public AsyncResponse(final UUID uuid) {
		this.ID = uuid;
	}
	
	public AsyncResponse()
	{}

	public UUID getID() {
		return ID;
	}
	
	

}
