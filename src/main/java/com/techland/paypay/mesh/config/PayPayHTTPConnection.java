package com.techland.paypay.mesh.config;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public final class PayPayHTTPConnection {
	
	private static Map<String,HttpURLConnection> connections = new HashMap<>();
	
	private PayPayHTTPConnection()
	{}
	
	public static HttpURLConnection getInstance(String postedurl)
	{
		HttpURLConnection conn = connections.get(postedurl) ;
		try {
			if(conn == null)
			{
				URL url = new URL(postedurl);
				conn = (HttpURLConnection) url.openConnection();
				connections.put(postedurl, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return conn;	
	}

}
