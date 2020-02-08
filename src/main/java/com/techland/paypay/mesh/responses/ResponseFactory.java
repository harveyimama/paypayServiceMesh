package com.techland.paypay.mesh.responses;

import com.techland.paypay.mesh.contracts.ServiceResponse;

public final class ResponseFactory  {

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
	
	public static <T extends ServiceResponse> T MockResponse(T response)
	{
		response.setAll("Test sucessful", 0, true);
		return response;
	}
	
	public static <T extends ServiceResponse> T HTTPResponse(T response)
	{
		response.setAll("Error Connecting to service", 302, false);
		return response;
	}
}
