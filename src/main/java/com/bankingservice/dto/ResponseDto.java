package com.bankingservice.dto;

public class ResponseDto {
	private String id;
	private String location;
	private String info1;
	private String info2;
	public ResponseDto(String id, String location, String info1, String info2) {
		super();
		this.id = id;
		this.location = location;
		this.info1 = info1;
		this.info2 = info2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	
	
}
