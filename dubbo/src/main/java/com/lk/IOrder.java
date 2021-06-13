package com.lk;
/**
 * 定义接口，没有实现类，但是通过接口赖于数据库发生访问关系，通过反射代理来搞定
 * 权限认证，
 * @author mac1094
 *
 */
		

public interface IOrder {
	public IOrder selectById(long id);
		
	

}
