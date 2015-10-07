package com.samson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.samson.model.DescribeRow;
import com.samson.model.Order;
import com.samson.model.OrderCaseSelect;
import com.samson.model.OrderRowMapper;
import com.samson.model.OrderWithSum;
import com.samson.model.TableInSalesDept;

public class OrderDAOImpl implements OrderDAO{
	
	private JdbcTemplate jdbcTemplate;
	private String sessionId;
	
	
	public OrderDAOImpl(DataSource dataSource, String sessionId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.sessionId=sessionId;
	}
	
	@Override
	public List<Order> getAll() {
	    String sql = "SELECT * FROM Orders"+sessionId;
	    List<Order> listOrder = jdbcTemplate.query(sql, new OrderRowMapper());
	 
	    return listOrder;
	}

	@Override
	public void delete(int id) {
	    String sql = "DELETE FROM Orders"+sessionId+" WHERE id=?";
	    jdbcTemplate.update(sql, id);
	}

	@Override
	public void add(Order addOrder) {
        // insert
        String sql = "INSERT INTO Orders"+sessionId+" (date, product_id, qty, amount, customer_id)"
                    + " VALUES (?, ?, ?, ?, ?)";
	    jdbcTemplate.update(sql, addOrder.getDate(), addOrder.getProductId(), 
	    		addOrder.getQty(), addOrder.getAmount(), addOrder.getCustomerId());		
	}

	@Override
	public Order getById(int id) {
	    String sql = "SELECT * FROM Orders"+sessionId+" WHERE id=?";
	    Order order = (Order)jdbcTemplate.queryForObject(sql, 
	    		new Object[] { id }, new OrderRowMapper());
			
		return order;
	}

	@Override
	public List<OrderCaseSelect> caseSelect() {
	    String sql = "SELECT date,customer_id,amount, " 
	    		+ " CASE"
	    		+ " WHEN amount <= 5000 THEN 'Малый' " 
	    		+ " WHEN amount BETWEEN 5000 AND 15000 THEN 'Средний' " 
	    		+ " WHEN amount > 15000 THEN 'Крупный' "
	    		+ " END as size"
	    		+ " FROM Orders"+sessionId;
	    
	    List<OrderCaseSelect> listOrder = jdbcTemplate.query(sql, new RowMapper<OrderCaseSelect>() {
	 
	        @Override
	        public OrderCaseSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	OrderCaseSelect aOrder = new OrderCaseSelect();
	        	
	        	aOrder.setDate(rs.getTimestamp("date"));
	        	aOrder.setAmount(rs.getInt("amount"));
	        	aOrder.setCustomerId(rs.getInt("customer_id"));
	 
	            return aOrder;
	        }
	 
	    });
	 
	    return listOrder;
	}

	@Override
	public List<OrderWithSum> havingSelect() {
	    String sql = "SELECT customer_id, SUM(amount) FROM Orders"+sessionId  
	    		+ " GROUP BY customer_id " 
	    		+ " HAVING SUM(amount) > 20000" ;
	    
	    List<OrderWithSum> listOrder = jdbcTemplate.query(sql, new RowMapper<OrderWithSum>() {
	 
	        @Override
	        public OrderWithSum mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	OrderWithSum aOrder = new OrderWithSum();
	        	
	        	aOrder.setSum(rs.getInt("SUM(amount)"));
	        	aOrder.setCustomerId(rs.getInt("customer_id"));
	 
	            return aOrder;
	        }
	 
	    });
	 
	    return listOrder;
	}

	@Override
	public Order selectMaxAmountOrder() {
	    String sql = "SELECT * FROM Orders"+sessionId+" WHERE " 
	    		+ " amount = (SELECT MAX(amount) FROM Orders"+sessionId+")";
	    
	    Order order = (Order) (Order)jdbcTemplate.queryForObject(sql, 
	    		new OrderRowMapper());
		
	    return order;
	}

	@Override
	public List<Order> selectMaxMinAmountOrder() {
	    String sql = "SELECT * FROM Orders"+sessionId
	    		+ " WHERE amount = (SELECT MAX(amount) FROM Orders"+sessionId+")" 
	    		+ "UNION "
	    		+ "SELECT * FROM Orders"+sessionId 
	    		+ " WHERE amount = (SELECT MIN(amount) FROM Orders"+sessionId+")";
		    
	    List<Order> listOrder = jdbcTemplate.query(sql, new OrderRowMapper()); 
		 
		return listOrder;
	}

	@Override
	public List<OrderWithSum> selectHavingWithRollup() {
	    String sql = "SELECT customer_id, SUM(amount) FROM Orders"+sessionId 
	    		+ " GROUP BY customer_id WITH ROLLUP " ;
	    
	    List<OrderWithSum> listOrder = jdbcTemplate.query(sql, new RowMapper<OrderWithSum>() {
	 
	        @Override
	        public OrderWithSum mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	OrderWithSum aOrder = new OrderWithSum();
	        	
	        	aOrder.setSum(rs.getInt("SUM(amount)"));
	        	aOrder.setCustomerId(rs.getInt("customer_id"));
	 
	            return aOrder;
	        }
	 
	    });
	 
	    return listOrder;
	}

	@Override
	public List<DescribeRow> describeOrder() {
	    String sql = "Describe Orders"+sessionId;
	    
	    List<DescribeRow> listDescribeRow = jdbcTemplate.query(sql, new RowMapper<DescribeRow>() {
	 
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
		String sql = "CREATE TABLE `Orders"+ sessionId +"` "
				+ "(`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,"
				+ "`date` date DEFAULT NULL," 
				+ "`product_id` BIGINT(20) UNSIGNED NOT NULL," 
				+ " `qty` int(10) unsigned DEFAULT NULL," 
				+ "`amount` decimal(10,2) DEFAULT NULL," 
				+ "`customer_id` BIGINT(20) UNSIGNED NOT NULL,"
				+ "PRIMARY KEY (`id`)," 
				+ "FOREIGN KEY (product_id) REFERENCES Products"+ sessionId +" (id) ON UPDATE CASCADE ON DELETE CASCADE," 
				+ "FOREIGN KEY (customer_id) REFERENCES Customers"+ sessionId +" (id) ON UPDATE CASCADE ON DELETE CASCADE"
				+ ") ENGINE=InnoDB CHARSET=utf8";
			
		jdbcTemplate.execute(sql); 
		
		sql = "INSERT INTO `Orders"+sessionId+"` VALUES "
				+ "(1012, '2007-12-12', 5, 8, 4500, 533),"
				+ "(1013,'2007-12-12', 2, 14, 22000, 536)";
	
		jdbcTemplate.update(sql);
		
	}
	
	@Override
	public void deleteTable() {
		String sql = "DROP TABLE IF EXISTS Orders"+ sessionId;
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

			if (tableInSalesDept.getTableName().equalsIgnoreCase("orders"+sessionId)) {
				return true;
			}
	    }
	    return false;
	}

}
