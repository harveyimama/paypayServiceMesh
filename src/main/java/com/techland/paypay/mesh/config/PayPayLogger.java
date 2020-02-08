package com.techland.paypay.mesh.config;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.techland.paypay.mesh.contracts.Service;

@Component
public class PayPayLogger<T extends Service<U>, U>  implements Runnable {
	
	final Logger logger = LoggerFactory.getLogger(PayPayLogger.class);
	private T logService;
	

	private  StringBuilder logs = new StringBuilder();
	
	public  void doLog(T serv,final String... items)
	{
		logService = serv;
		ExecutorService  service = PayPayThread.startThreader();
		logs.append("\r\n".concat("====Logs Start here=====").concat("\r\n"));  
		for(String  item  :  items )
		{
			logs.append(item.concat("\r\n"));
		}
		logs.append("====Logs end here=======");
			
		service.execute(this);
		
	}
	
	@Override
	public void run() {
		logger.info( this.logs.toString(),logService.getName());
	}

	
	

}
