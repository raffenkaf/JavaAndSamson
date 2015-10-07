package com.samson.hibernate.service;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.samson.hibernate.model.HibernateCustomer;

@Service("customerServiceImpl")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public HibernateCustomer findCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (HibernateCustomer)session.get(HibernateCustomer.class, id);
	}

	@Override
	public void saveCustomer(HibernateCustomer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(customer);
	}

	@Override
	public void deleteCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		HibernateCustomer customer = (HibernateCustomer)session.get(HibernateCustomer.class, id);
		session.delete(customer);
	}

	@Override
	public List<HibernateCustomer> findAllCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM "+HibernateCustomer.class.getName());
		return query.list();
	}

}
