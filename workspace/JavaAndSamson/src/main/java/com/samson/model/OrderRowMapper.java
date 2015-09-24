package com.samson.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order aOrder = new Order();
 
		aOrder.setId(rs.getInt("id"));
        aOrder.setDate(rs.getTimestamp("date"));
        aOrder.setProductId(rs.getInt("product_id"));
        aOrder.setQty(rs.getInt("qty"));
        aOrder.setAmount(rs.getInt("amount"));
        aOrder.setCustomerId(rs.getInt("customer_id"));
 
        return aOrder;
	}

}
