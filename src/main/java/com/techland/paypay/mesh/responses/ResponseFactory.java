package com.techland.paypay.mesh.responses;

import com.techland.paypay.mesh.contracts.ServiceResponse;
import com.techland.paypay.mesh.helper.Messages;

public final class ResponseFactory  {

	public static <T extends ServiceResponse> T NotNull(T response)
	{
		response.setAll(Messages.NULL_MESSAGE, 10, false);
		return response;
	}
	
	public static <T extends ServiceResponse> T TryCatch(T response)
	{
		response.setAll(Messages.SERVER_ERROR, 1, false);
		return response;
	}

	public static <T extends ServiceResponse> T NullResponse(T response)
	{
		response.setAll(Messages.NULL_RESPONSE, 2, false);
		return response;
	}
	
	public static <T extends ServiceResponse> T MockResponse(T response)
	{
		response.setAll(Messages.MOCK_RESPONSE, 0, true);
		return response;
	}
	
	public static <T extends ServiceResponse> T HTTPResponse(T response)
	{
		response.setAll(Messages.HTTP_ERROR, 302, false);
		return response;
	}
}
