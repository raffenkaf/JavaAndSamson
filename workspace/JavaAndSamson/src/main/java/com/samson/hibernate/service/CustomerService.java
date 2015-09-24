package com.samson.hibernate.service;

import java.util.List;

import com.samson.hibernate.model.HibernateCustomer;

public interface CustomerService {
	HibernateCustomer findCustomerById(int id);
    
    void saveCustomer(HibernateCustomer customer);
     
    void deleteCustomerById(int id);
     
    List<HibernateCustomer> findAllCustomers();
}
