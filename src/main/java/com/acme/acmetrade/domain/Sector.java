package com.acme.acmetrade.domain;

public class Sector {
	
	private int id;
	private String sectorName;
	private String sectorDesc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getSectorDesc() {
		return sectorDesc;
	}
	public void setSectorDesc(String sectorDesc) {
		this.sectorDesc = sectorDesc;
	}
	
	public Sector() {};
	
	public Sector(int id, String sectorName, String sectorDesc) {
		this.id = id;
		this.sectorName = sectorName;
		this.sectorDesc = sectorDesc;
	}
}
