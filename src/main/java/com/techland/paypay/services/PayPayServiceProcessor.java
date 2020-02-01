package com.techland.paypay.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.techland.paypay.config.PayPayThread;
import com.techland.paypay.config.Settings;
import com.techland.paypay.contracts.ProcessorType;
import com.techland.paypay.contracts.Service;
import com.techland.paypay.processorTypes.GeneralProcessor;
import com.techland.paypay.processorTypes.MockProcessor;

public final class PayPayServiceProcessor<R extends Service<S>, S,W extends ProcessorType> {

	private final ReentrantLock lock = new ReentrantLock();
	

	@SuppressWarnings("unchecked")
	<T> T processService(final R service, final S data, W processor) {
		T ret = null;
		try {
			
			if (Settings.MOCK)
				processor = (W) new MockProcessor();

			if (service.getServiceType().getReturnType())
				ret =  processor.doProcessing(service.getURL(), data.toString(), service.getContentType(),
						service.getAuthorization(), service.isFormParam(), service.getServiceType().getRequestType());
			else {
				ExecutorService executor = PayPayThread.startThreader();

				if (lock.tryLock(Settings.THREAD_LOCK_TIMEOUT, TimeUnit.SECONDS)) {
					try {
						processor.setProcessorValues(service.getURL(), data.toString(),
								service.getContentType(), service.getAuthorization(), service.isFormParam(),
								service.getServiceType().getRequestType());
						
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
