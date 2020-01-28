package com.techland.paypay.contracts;

public interface Service<T> {
	

     <R extends ServiceResponse> R doRequst(final T request );
}
