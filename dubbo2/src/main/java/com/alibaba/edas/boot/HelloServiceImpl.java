package com.alibaba.edas.boot;
/**
 * 写一个类，用于实现接口，暴露的服务接口
 */

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements IHelloService{

	public String sayHello(String name) {
		return "Hello,"+name+"(from Dubbo with Spring Boot)";
	}
	

}
