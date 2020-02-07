package com.techland.paypay.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PayPayThread {
	
	private static ExecutorService executor;
	private PayPayThread()
	{}
	
	public static ExecutorService startThreader()
	{
		if(executor == null)
			 executor = Executors.newFixedThreadPool( Settings.NUMBER_OF_THREADS ); 
		
		return executor; 
	}

}
