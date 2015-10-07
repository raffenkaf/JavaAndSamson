package com.samson.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerJoinOrderRowMapper implements RowMapper<CustomerJoinOrder>{

	@Override
	public CustomerJoinOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerJoinOrder aCustomerJoinOrder = new CustomerJoinOrder();
      	 
    	aCustomerJoinOrder.setCustomerId(rs.getInt("customerId"));
    	aCustomerJoinOrder.setCustomerName(rs.getString("name"));   	
    	aCustomerJoinOrder.setOrderId(rs.getInt("orderId"));
    	aCustomerJoinOrder.setOrderCustomerId(rs.getInt("customer_id"));
    	aCustomerJoinOrder.setOrderProductId(rs.getInt("product_id"));

        return aCustomerJoinOrder;
		
	}

}
