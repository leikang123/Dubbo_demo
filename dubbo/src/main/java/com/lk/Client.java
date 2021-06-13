package com.lk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
	public static <T> T getBean(Class interfaceClass) {
		
	   /**
	    * 动态代理需要通过字节码重组，相当于spring Aop
	    */
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new InvocationHandler() {
			
			/**
			 * 
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				return new Order((long)args[0],"购买了开课吧课程");
			}
		});		
			
	}
	public static void main(String[] args) {
		IOrder orderImpl = Client.getBean(IOrder.class);
		System.out.println(orderImpl.selectById(8));
	}

}
