package com.lk;

public class Order {
	/**
	 * 订单编号
	 */
	
	private long id;
	/**
	 * 订单描述
	 */
	private String desc;
	public Order(long id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}
	public Order() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
