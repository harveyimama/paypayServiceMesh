package com.techland.paypay.serviceTypes;

import java.util.HashMap;
import java.util.Map;

import com.techland.paypay.contracts.ServiceType;
import com.techland.paypay.ennums.ServiceTypeEnum;

public class ServiceTypeFactory {

	private static Map<ServiceTypeEnum, ServiceType> map;

	private ServiceTypeFactory() {

	}

	public static  ServiceType getInstance(ServiceTypeEnum type) {

		ServiceType inst = map.get(type);

		if (inst == null) {

			switch (type) {

			case GETWITHNORETURN:
				inst = new GetServiceWithNoReturn();
			case GETWITHRETURN:
				inst = new GetServiceWithReturn();
			case POSTWITHNORETURN:
				inst = new PostServiceWithReturn();
			case POSTWITHRETURN:
				inst = new PostServiceWithReturn();
			}

			map.put(type, inst);
		}

		return inst;

	}

}
