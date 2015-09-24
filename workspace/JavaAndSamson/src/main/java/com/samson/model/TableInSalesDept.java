package com.samson.model;

public class TableInSalesDept {
	String tableName;
	
	public TableInSalesDept(){
		
	}

	public TableInSalesDept(String tableName){
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
