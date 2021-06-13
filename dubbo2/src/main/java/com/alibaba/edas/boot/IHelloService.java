package com.alibaba.edas.boot;
/**
 * dubbo中服务形式都是以接口形式开发的，所以接写接口，
 * 接口里面有很多方法调用。
 * @author mac1094
 *
 */

public  interface IHelloService {
	String sayHello(String str);
	

}
