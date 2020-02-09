package com.techland.paypay.mesh.contracts;

public interface ServiceRequest {
	
	@Override
	public String toString();
	
	
	public boolean isNull();
	
	public boolean isAsync();
	
	public void setAsync(boolean async);

}
