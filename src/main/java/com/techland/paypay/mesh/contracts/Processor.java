package com.techland.paypay.mesh.contracts;

public interface Processor <R extends Service<S>, S,W extends ProcessorType> {
	
	<T> T processService(final R service, final S data, W processor);

}
