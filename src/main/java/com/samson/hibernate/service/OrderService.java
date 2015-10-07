package com.samson.hibernate.service;

import java.util.List;
import com.samson.hibernate.model.HibernateOrder;

public interface OrderService {
    HibernateOrder findOrderById(int id);
    
    void saveOrder(HibernateOrder order);
     
    void deleteOrderById(int id);
     
    List<HibernateOrder> findAllOrders();
}
