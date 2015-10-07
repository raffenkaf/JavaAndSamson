package com.samson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.samson.model.Customer;
import com.samson.model.CustomerRowMapper;
import com.samson.model.CustomerWithAliasesSelect;
import com.samson.model.DescribeRow;
import com.samson.model.TableInSalesDept;

public class CustomerDAOImpl implements CustomerDAO{
	
	private JdbcTemplate jdbcTemplate;
	private String sessionId;
	
	public CustomerDAOImpl(DataSource dataSource, String sessionId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.sessionId=sessionId;
	}
	
	@Override
	public List<Customer> getAll() {
	    String sql = "SELECT * FROM Customers"+ sessionId;
	    List<Customer> listCustomer = jdbcTemplate.query(sql, new CustomerRowMapper());
 
	    return listCustomer;
	}

	@Override
	public void delete(int id) {
	    String sql = "DELETE FROM Customers"+ sessionId +" WHERE id=?";
	    jdbcTemplate.update(sql, id);
	}

	@Override
	public void add(Customer addCustomer) {
        // insert
        String sql = "INSERT INTO Customers"+ sessionId +" (name, phone, address, rating)"
                    + " VALUES (?, ?, ?, ?)";
	    jdbcTemplate.update(sql, addCustomer.getName(), addCustomer.getPhone(), 
	    		addCustomer.getAddress(), addCustomer.getRating());				
	}

	@Override
	public Customer getById(int id) {
	    String sql = "SELECT * FROM Customers"+ sessionId +" WHERE id=?";
	    return (Customer)jdbcTemplate.queryForObject(sql, 
	    		new Object[]{id}, new CustomerRowMapper());
	}

	@Override
	public List<Customer> distinctSelect() {
	    String sql = "SELECT DISTINCT rating/1000 FROM Customers"+ sessionId 
	    		+ " ORDER BY rating DESC, name";
	    List<Customer> listCustomer = 
	    		jdbcTemplate.query(sql,	new RowMapper<Customer>() {
	 
	        @Override
	        public Customer mapRow(ResultSet rs, int rowNum) 
	        		throws SQLException {
	        	Customer aCustomer = new Customer();
	        	
	        	aCustomer.setRating(rs.getFloat("rating/1000")*1000);
	 
	            return aCustomer;
	        }
	 
	    });
	 
	    return listCustomer;
	}

	@Override
	public List<Customer> likeSelect() {
	    String sql = "SELECT * FROM Customers"+ sessionId 
	    		+ " WHERE name LIKE 'OOO%' OR rating>1000";
	    
		    List<Customer> listCustomer = 
		    		jdbcTemplate.query(sql, new CustomerRowMapper()); 
		 
		    return listCustomer;
	}

	@Override
	public List<CustomerWithAliasesSelect> selectWithAliases() {
	    String sql = "SELECT L.name as LName, R.name as RName"
	    		+ " FROM Customers"+ sessionId +" L, Customers"+ sessionId +" R"
	    		+ " WHERE L.rating = R.rating AND L.name < R.name";
		
	    List<CustomerWithAliasesSelect> listCustomer = 
	    		jdbcTemplate.query(sql,	new RowMapper<CustomerWithAliasesSelect>() {
		 
		        @Override
		        public CustomerWithAliasesSelect mapRow(ResultSet rs, int rowNum) 
		        		throws SQLException {
		        	CustomerWithAliasesSelect aCustomer = new CustomerWithAliasesSelect();
		        	
		        	aCustomer.setOtherCustomerName(rs.getString("RName"));
		        	aCustomer.setName(rs.getString("LName"));
		 
		            return aCustomer;
		        }
		 
		    });
		 
		    return listCustomer;
	}

	@Override
	public List<Customer> selectWithIdIn() {
	    String sql = "SELECT * FROM Customers"+ sessionId +" WHERE id IN" 
	    		+ " (SELECT DISTINCT customer_id FROM Orders"+ sessionId +")";
	    
	    List<Customer> listCustomer = 
	    		jdbcTemplate.query(sql, new CustomerRowMapper());
	 
	    return listCustomer;
	}

	@Override
	public List<Customer> selectAllProductOrder() {
	    String sql = "SELECT * FROM Customers"+ sessionId +" WHERE NOT EXISTS" 
	    		+ " (SELECT * FROM Products"+ sessionId +" WHERE NOT EXISTS" 
	    		+ " (SELECT * FROM Orders"+ sessionId 
	    		+ " WHERE product_id = Products"+sessionId+".id))";
	    
	    List<Customer> listCustomer = 
	    		jdbcTemplate.query(sql, new CustomerRowMapper());
	 
	    return listCustomer; 
	}

	@Override
	public List<DescribeRow> describeCustomer() {
	    String sql = "Describe Customers"+ sessionId;
	    
	    List<DescribeRow> listDescribeRow = 
	    		jdbcTemplate.query(sql, new RowMapper<DescribeRow>() {
	 
	        @Override
	        public DescribeRow mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	DescribeRow aDescribeRow = new DescribeRow();
	        	
	        	aDescribeRow.setField(rs.getString("Field"));
	        	aDescribeRow.setType(rs.getString("Type"));
	        	aDescribeRow.setIsNull(rs.getBoolean("Null"));
	        	aDescribeRow.setKey(rs.getString("Key"));
	        	aDescribeRow.setIsDefault(rs.getString("Default"));
	        	aDescribeRow.setExtra(rs.getString("Extra"));
	 
	            return aDescribeRow;
	        }
	 
	    });
	 
	    return listDescribeRow;
	}

	@Override
	public void create() {
		String sql = "CREATE TABLE `Customers"+ sessionId +"` (" 
				+ "`id` SERIAL,"
				+ "`name` varchar(100) DEFAULT NULL," 
				+ "`phone` varchar(20) DEFAULT NULL," 
				+ "`address` varchar(150) DEFAULT NULL," 
				+ "`rating` int(11) DEFAULT NULL," 
				+ "PRIMARY KEY (`id`)) "
				+ "ENGINE=InnoDB CHARSET=utf8";
		
		jdbcTemplate.execute(sql);
		
		sql = "INSERT INTO `Customers"+sessionId+"` VALUES "
				+ "(533,'OOO KysKys','313-48-48','Smolnaja St., 7',1000),"
				+ "(534,'Petrov','112-14-15','Rokotova St., 8',1500),"
				+ "(536,'Krulov','444-78-90','Zelenaja St., 22',1000)";	
		
		jdbcTemplate.update(sql);
	}

	@Override
	public void deleteTable() {
		String sql = "DROP TABLE IF EXISTS Customers"+ sessionId;
		jdbcTemplate.execute(sql);		
	}

	@Override
	public boolean isTableExist() {
	    String sql = "show tables";
	    
	    List<TableInSalesDept> listTablesInSalesDept = 
	    	jdbcTemplate.query(sql, new RowMapper<TableInSalesDept>(){

				@Override
				public TableInSalesDept mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					TableInSalesDept tablesInSalesDept = new TableInSalesDept();
					tablesInSalesDept.setTableName(rs.getString("Tables_in_javacv"));
					return tablesInSalesDept;
				}
	    });
	    
	    for (TableInSalesDept tableInSalesDept : listTablesInSalesDept) {

			if (tableInSalesDept.getTableName().equalsIgnoreCase("customers"+sessionId)) {
				return true;
			}
	    }
	    return false;
	}

}
