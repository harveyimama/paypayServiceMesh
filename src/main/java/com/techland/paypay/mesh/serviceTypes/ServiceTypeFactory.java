package com.techland.paypay.mesh.serviceTypes;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.techland.paypay.mesh.contracts.ServiceType;
import com.techland.paypay.mesh.ennums.ServiceTypeEnum;

public class ServiceTypeFactory {

	@Autowired
	private static Map<ServiceTypeEnum, ServiceType> map = new HashMap<>();

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
