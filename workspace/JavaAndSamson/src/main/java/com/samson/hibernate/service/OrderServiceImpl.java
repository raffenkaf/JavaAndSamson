package com.samson.hibernate.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samson.hibernate.model.HibernateOrder;

@Service("orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public HibernateOrder findOrderById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (HibernateOrder)session.get(HibernateOrder.class, id);
	}

	@Override
	public void saveOrder(HibernateOrder order) {
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
	}

	@Override
	public void deleteOrderById(int id) {
		Session session = sessionFactory.getCurrentSession();
		HibernateOrder order = (HibernateOrder)session.get(HibernateOrder.class, id);
		session.delete(order);
	}

	@Override
	public List<HibernateOrder> findAllOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM "+HibernateOrder.class.getName());
		return query.list();
	}

}
