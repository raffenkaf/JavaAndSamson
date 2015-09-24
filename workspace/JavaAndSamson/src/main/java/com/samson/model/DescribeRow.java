package com.samson.model;

public class DescribeRow {
	private String field;
	private String type;
	private boolean isNull;
	private String key;
	private String isDefault;
	private String extra;
	
	public DescribeRow(String field, String type, boolean isNull, String key,String isDefault,String extra) {
		this.field = field;
		this.type = type;
		this.isNull = isNull;
		this.key = key;
		this.isDefault = isDefault;
		this.extra = extra;
	}
	
	public DescribeRow() {
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIsNull() {
		return isNull;
	}
	public void setIsNull(boolean isNull) {
		this.isNull = isNull;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
}
