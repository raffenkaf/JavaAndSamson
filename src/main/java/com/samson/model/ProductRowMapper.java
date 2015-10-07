package com.samson.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Product aProduct = new Product();
   	 
    	aProduct.setId(rs.getInt("id"));
    	aProduct.setDescription(rs.getString("description"));
    	aProduct.setDetails(rs.getString("details"));
    	aProduct.setPrice(rs.getInt("price"));
    	
    	return aProduct;
	}

}
