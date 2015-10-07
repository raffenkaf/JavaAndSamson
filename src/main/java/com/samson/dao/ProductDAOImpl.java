package com.samson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.samson.model.DescribeRow;
import com.samson.model.Product;
import com.samson.model.ProductRowMapper;
import com.samson.model.TableInSalesDept;

public class ProductDAOImpl implements ProductDAO{
	
	private JdbcTemplate jdbcTemplate;
	String sessionId;
	
	public ProductDAOImpl(DataSource dataSource, String sessionId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.sessionId = sessionId;
	}
	
	@Override
	public List<Product> getAll() {
	    String sql = "SELECT * FROM Products"+sessionId;
	    List<Product> listProduct = jdbcTemplate.query(sql, new ProductRowMapper());
	 
	    return listProduct;
	}

	@Override
	public void delete(int id) {
	    String sql = "DELETE FROM Products"+sessionId+" WHERE id = ?";
	    jdbcTemplate.update(sql, id);
	}

	@Override
	public void add(Product addProduct) {
        // insert
        String sql = "INSERT INTO Products"+sessionId+" (description, details, price)"
                    + " VALUES (?, ?, ?)";
	    jdbcTemplate.update(sql, addProduct.getDescription(), addProduct.getDetails(), 
	    		addProduct.getPrice());	
		
	}

	@Override
	public Product getById(int id) {
	    String sql = "SELECT * FROM Products"+sessionId+" WHERE id=?";
	    return (Product)jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper()); 
	}

	@Override
	public List<DescribeRow> describeProduct() {
	    String sql = "Describe Products"+sessionId;
	    
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
	public List<Product> selectExist() {
		String sql = "SELECT * FROM Products"+sessionId
		+ " WHERE EXISTS" 
		+ "(SELECT * FROM Orders"+sessionId
		+ " WHERE product_id = Products"+sessionId+".id)"; 
		
	    List<Product> listProduct = jdbcTemplate.query(sql, new ProductRowMapper());
		 
	    return listProduct;
	}

	@Override
	public void create() {
		String sql = "CREATE TABLE `Products"+sessionId+"` "
				+ "(`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,"
				+ "`description` varchar(100) DEFAULT NULL,"
				+ "`details` text, "
				+ "`price` decimal(8,2) DEFAULT NULL, "
				+ "PRIMARY KEY (`id`)) "
				+ "ENGINE=InnoDB CHARSET=utf8"; 
					
		jdbcTemplate.execute(sql);
		
		sql = "INSERT INTO `Products"+sessionId+"` VALUES "
				+ "(1,'Obogrevatel 12g','Power 400/800/1200 W',1145),"
				+ "(2,'Gril ST14','Power 1200 W',2115),"
				+ "(3,'Kofevarka EKL32','Power 450 W',710),"
				+ "(4,'Chainik MN3','Power 2200 W',925),"
				+ "(5,'Utug AB20','Power 1400 W',518)";
	
		jdbcTemplate.update(sql);
		
	}
	
	@Override
	public void deleteTable() {
		String sql = "DROP TABLE IF EXISTS Products"+ sessionId;
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

			if (tableInSalesDept.getTableName().equalsIgnoreCase("products"+sessionId)) {
				return true;
			}
	    }
	    return false;
	}

}
