package com.techland.paypay.contracts;

public interface ProcessorType {

	<T> T doProcessing(final String url, final String data, final String contentType, final String authorization,
			final boolean isFormParam);

}
