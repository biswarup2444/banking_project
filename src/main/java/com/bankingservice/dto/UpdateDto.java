package com.bankingservice.dto;

public class UpdateDto {
	private String id;
	private String field;
	private String newval;
	public UpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateDto(String id, String field, String newval) {
		super();
		this.id = id;
		this.field = field;
		this.newval = newval;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getNewval() {
		return newval;
	}
	public void setNewval(String newval) {
		this.newval = newval;
	}
}
