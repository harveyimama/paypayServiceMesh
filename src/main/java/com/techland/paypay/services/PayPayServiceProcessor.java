package com.techland.paypay.services;

import com.techland.paypay.contracts.Service;
import com.techland.paypay.processorTypes.GeneralProcessor;


public final class PayPayServiceProcessor<R extends Service<S>, S> {
	
     <T> T processService(final R service,final S data)
    {
    	
    	return new GeneralProcessor().doProcessing(service.getURL(),  data.toString(), service.getContentType(), 
    			service.getSuthorization(), service.isFormParam());
    }
	


}
