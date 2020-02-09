package com.techland.paypay.mesh.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.config.PayPayThread;
import com.techland.paypay.mesh.config.Settings;
import com.techland.paypay.mesh.contracts.Processor;
import com.techland.paypay.mesh.contracts.ProcessorType;
import com.techland.paypay.mesh.contracts.Service;
import com.techland.paypay.mesh.processorTypes.MockProcessor;

@Component
public final class PayPayServiceProcessor<R extends Service<S>, S,W extends ProcessorType> implements  Processor<R,S,W>  {

	private final ReentrantLock lock = new ReentrantLock();
	@Autowired
	private MockProcessor mockProcessor;
	

	@SuppressWarnings("unchecked")
		public <T> T processService(final R service, final S data, W processor) {
		T ret = null;
		try {
			
			if (Settings.MOCK)
				processor = (W) mockProcessor;

			if (service.getServiceType().getReturnType()) 
				ret =  processor.doProcessing(service.getURL(), data.toString(), service.getContentType(),
						service.getAuthorization(), service.isFormParam(), service.getServiceType().getRequestType(),service.getConnectTimeOut()
						,service.getReadTimeOut());
			else {
				ExecutorService executor = PayPayThread.startThreader();

				if (lock.tryLock(Settings.THREAD_LOCK_TIMEOUT, TimeUnit.SECONDS)) {
					try {
						processor.setProcessorValues(service.getURL(), data.toString(),
								service.getContentType(), service.getAuthorization(), service.isFormParam(),
								service.getServiceType().getRequestType(),service.getConnectTimeOut()
								,service.getReadTimeOut());
						
						executor.execute(processor);
					} finally {
						lock.unlock();
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ret;

	}

}
