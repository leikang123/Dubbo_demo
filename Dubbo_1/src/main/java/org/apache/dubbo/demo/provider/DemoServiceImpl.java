package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.DemoService;

// 此类用来实现服务端接口的功能实现
public class DemoServiceImpl implements DemoService{

	public String sayHello(String name) {
	
		return "hello" +name;
	}

}
