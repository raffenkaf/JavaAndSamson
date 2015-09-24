package com.samson.dao;
import java.util.List;

import com.samson.model.DescribeRow;
import com.samson.model.Order;
import com.samson.model.OrderCaseSelect;
import com.samson.model.OrderWithSum;


public interface OrderDAO {
	
	public List<DescribeRow> describeOrder();
	
	public List<Order> getAll();
	
    public void delete(int id);
    
    public void add(Order addOrder);
     
    public Order getById(int id);
 
    public List<OrderCaseSelect> caseSelect();
    
    public List<OrderWithSum> havingSelect();
    
    public Order selectMaxAmountOrder();
    
    public List<Order> selectMaxMinAmountOrder();
    
    public List<OrderWithSum> selectHavingWithRollup();
    
    public boolean isTableExist();

    public void create();
    
    public void deleteTable();
}
