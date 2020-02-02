package com.techland.paypay.config;

public final class Settings {
	
	final static int NUMBER_OF_THREADS = 100;
	public final static int THREAD_LOCK_TIMEOUT = 8;
	public static boolean MOCK;
	final static int ASYNC_WAIT_TIME = 2;
	
	public  static void setTest()
	{
		MOCK = true;
	}

	public  static void setProd()
	{
		MOCK = false;
	}
}
