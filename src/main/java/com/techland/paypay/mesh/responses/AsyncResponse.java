package com.techland.paypay.mesh.responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.contracts.ServiceResponse;


@Component
public class AsyncResponse extends ServiceResponse {
	
	private  String ID;
	
	
	public AsyncResponse()
	{
		super();
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}

	
	
	

}
