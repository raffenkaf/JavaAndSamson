package com.samson.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.samson.model.*;

public class CustomerJoinOrderDAOImpl implements CustomerJoinOrderDAO{

	private JdbcTemplate jdbcTemplate;
	private String sessionId;
	
	public CustomerJoinOrderDAOImpl(DataSource dataSource, 
			String sessionId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.sessionId = sessionId;
	}
	
	@Override
	public List<CustomerJoinOrder> selectCrossJoin() {
		String sql = "SELECT c.id as customerId, name, " + 
			" o.id as orderId, product_id, customer_id" + 
			" FROM Customers"+ sessionId +" as c CROSS JOIN Orders"+ sessionId +" as o";
		
		List<CustomerJoinOrder> listCustomerJoinOrder = jdbcTemplate.query(sql,
				new CustomerJoinOrderRowMapper());
		
		return listCustomerJoinOrder;
	}

	@Override
	public List<CustomerJoinOrder> selectNaturalJoin() {
		String sql = "SELECT c.id as customerId, name, " + 
			" o.id as orderId, product_id, customer_id" + 
			" FROM Customers"+ sessionId +" as c NATURAL JOIN Orders"+ sessionId +" as o";
			
		List<CustomerJoinOrder> listCustomerJoinOrder = jdbcTemplate.query(sql,
			new CustomerJoinOrderRowMapper());
			
		return listCustomerJoinOrder;
	}

	@Override
	public List<CustomerJoinOrder> selectJoinUseId() {
		String sql = "SELECT c.id as customerId, name, " + 
			" o.id as orderId, product_id, customer_id" + 
			" FROM Customers"+ sessionId +" as c JOIN Orders"+ sessionId +" as o USING (id)";
			
		List<CustomerJoinOrder> listCustomerJoinOrder = jdbcTemplate.query(sql,
			new CustomerJoinOrderRowMapper());
			
		return listCustomerJoinOrder;
	}

	@Override
	public List<CustomerJoinOrder> selectJoinOn() {
		String sql = "SELECT c.id as customerId, name, " + 
			" o.id as orderId, product_id, customer_id" + 
			" FROM Customers"+ sessionId +" as c JOIN Orders"+ sessionId +" as o ON(c.id = o.customer_id)";
				
		List<CustomerJoinOrder> listCustomerJoinOrder = jdbcTemplate.query(sql,
			new CustomerJoinOrderRowMapper());
				
		return listCustomerJoinOrder;
	}

	@Override
	public List<CustomerJoinOrder> selectLeftOuterJoin() {
		String sql = "SELECT c.id as customerId, name, " + 
			" o.id as orderId, product_id, customer_id" + 
			" FROM Customers"+ sessionId +" c LEFT OUTER JOIN Orders"+ sessionId +" o ON(c.id = o.customer_id)";
				
		List<CustomerJoinOrder> listCustomerJoinOrder = jdbcTemplate.query(sql,
			new CustomerJoinOrderRowMapper());
				
		return listCustomerJoinOrder;
	}
	
}
