package com.techland.paypay.responses;

import com.techland.paypay.contracts.ServiceResponse;

public final class SystemResponse  {

	public static <T extends ServiceResponse> T NotNull(T response)
	{
		response.setAll("Request Cannot be Null", 10, false);
		return response;
	}
	
	public static <T extends ServiceResponse> T TryCatch(T response)
	{
		response.setAll("Server Error Occured", 1, false);
		return response;
	}

	public static <T extends ServiceResponse> T NullResponse(T response)
	{
		response.setAll("No reponse from Service", 2, false);
		return response;
	}
}
