package com.samson.dao;

import java.util.List;

import com.samson.model.Customer;
import com.samson.model.CustomerWithAliasesSelect;
import com.samson.model.DescribeRow;

public interface CustomerDAO {
	
	public List<DescribeRow> describeCustomer();
	
	public List<Customer> getAll();
	
    public void delete(int id);
    
    public void add(Customer addOrder);
     
    public Customer getById(int id);
 
    public List<Customer> distinctSelect();
    
    public List<Customer> likeSelect();
    
    public List<CustomerWithAliasesSelect> selectWithAliases();
    
    public List<Customer> selectWithIdIn();
    
    public List<Customer> selectAllProductOrder();
    
    public boolean isTableExist();
    
    public void create();
    
    public void deleteTable();
}
