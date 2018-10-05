package com.acme.acmetrade.domain;

public class OrderTrans {
	
	private int transId;
	private int transOrderId;
	private String transCreationDate;	
	private String transOrderStatus ;	
	private String transLastUpdate;
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getTransOrderId() {
		return transOrderId;
	}
	public void setTransOrderId(int transOrderId) {
		this.transOrderId = transOrderId;
	}
	public String getTransCreationDate() {
		return transCreationDate;
	}
	public void setTransCreationDate(String transCreationDate) {
		this.transCreationDate = transCreationDate;
	}
	public String getTransOrderStatus() {
		return transOrderStatus;
	}
	public void setTransOrderStatus(String transOrderStatus) {
		this.transOrderStatus = transOrderStatus;
	}
	public String getTransLastUpdate() {
		return transLastUpdate;
	}
	public void setTransLastUpdate(String transLastUpdate) {
		this.transLastUpdate = transLastUpdate;
	}
	public OrderTrans() {};
	public OrderTrans(int transId, int transOrderId, String transCreationDate, String transOrderStatus,
			String transLastUpdate) {
		super();
		this.transId = transId;
		this.transOrderId = transOrderId;
		this.transCreationDate = transCreationDate;
		this.transOrderStatus = transOrderStatus;
		this.transLastUpdate = transLastUpdate;
	}
	
	
}
