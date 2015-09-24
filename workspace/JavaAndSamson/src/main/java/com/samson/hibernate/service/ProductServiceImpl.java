package com.samson.hibernate.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samson.hibernate.model.HibernateCustomer;
import com.samson.hibernate.model.HibernateOrder;
import com.samson.hibernate.model.HibernateProduct;

@Service("productServiceImpl")
@Transactional
public class ProductServiceImpl implements ProductService{
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public HibernateProduct findProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (HibernateProduct)session.get(HibernateProduct.class, id);
	}

	@Override
	public void saveProduct(HibernateProduct product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
	}

	@Override
	public void deleteProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		HibernateCustomer customer = (HibernateCustomer)session.get(HibernateCustomer.class, id);
		session.delete(customer);
	}

	@Override
	public List<HibernateProduct> findAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM "+HibernateProduct.class.getName());
		return query.list();
	}
}
