package com.acme.acmetrade.domain;

public class Order {

	private int traderId;
	private int orderId;
	private String symbol;	
	private String side ;
	private String orderType;	
	private float price;	
	private int shares;
	private String lastUpdate;

	public int getTraderId() {
		return traderId;
	}
	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Order() {};
	
	public Order(int traderId, int orderId, String symbol, String side, String orderType, float price, int shares,
			String lastUpdate) {
		super();
		this.traderId = traderId;
		this.orderId = orderId;
		this.symbol = symbol;
		this.side = side;
		this.orderType = orderType;
		this.price = price;
		this.shares = shares;
		this.lastUpdate = lastUpdate;
	}
		
}
