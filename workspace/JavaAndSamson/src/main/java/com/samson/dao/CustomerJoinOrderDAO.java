package com.samson.dao;
import java.util.List;

import com.samson.model.*;

public interface CustomerJoinOrderDAO {
	
	public List<CustomerJoinOrder> selectCrossJoin();
	
	public List<CustomerJoinOrder> selectNaturalJoin();

	public List<CustomerJoinOrder> selectJoinUseId();
	
	public List<CustomerJoinOrder> selectJoinOn();
	
	public List<CustomerJoinOrder> selectLeftOuterJoin();
}
