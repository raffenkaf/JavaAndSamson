package com.samson.dao;

import java.util.List;

import com.samson.model.DescribeRow;
import com.samson.model.Product;

public interface ProductDAO {
	
    public List<DescribeRow> describeProduct();
	
	public List<Product> getAll();
	
    public void delete(int id);
    
    public void add(Product addOrder);
     
    public Product getById(int id);
    
    public List<Product> selectExist(); 
    
    public boolean isTableExist();
    
    public void create();
    
    public void deleteTable();
}
