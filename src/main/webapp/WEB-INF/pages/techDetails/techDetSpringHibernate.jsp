<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	Hibernate сам создает таблицы(или апдейтит если уже существуют). 
	Ниже показан пример простого применения, но без сервис слоя,
    то есть модели + дао/имплементация + jsp. 
    Скачать можно <a href = "https://github.com/raffenkaf"> здесь </a>
    
	1. Модели
	
	<pre>
	
package com.samson.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="customers")
public class HibernateCustomer {
	public HibernateCustomer(){
	}
	
	public HibernateCustomer(int customerId, String name, 
			String phone, String address, float rating){
		setName(name);
		setPhone(phone);
		setAddress(address);
		setRating(rating);
		setCustomerId(customerId);
	}
	
	@Id
	@GenericGenerator(name="assigned" , strategy="assigned")
	@GeneratedValue(generator="assigned")
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "rating")
	private float rating;
	
    @OneToOne(mappedBy = "customer",cascade=CascadeType.ALL)
	private HibernateOrder order;

	...// getters, setters

}
///////////////////////////////////	
package com.samson.hibernate.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class HibernateOrder {
	HibernateOrder(){
	}
	
	public HibernateOrder(int id, int qty, int amount) {
		setAmount(amount);
		setOrderId(id);
		setQty(qty);		
	}
	
	@Id
	@Column(name = "order_id")
	@GeneratedValue
	private int orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(name = "amount")
	private int amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="product_id"	)
	private HibernateProduct product;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="customer_id"	)
	private HibernateCustomer customer;

	...// getters, setters

}
///////////////////////////////////
package com.samson.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class HibernateProduct {
	public HibernateProduct() {
	}
	
	public HibernateProduct(int id, String description, String details, int price) {
		setProductId(id);
		setDescription(description);
		setDetails(details);
		setPrice(price);
	}
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue
	private int productId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "price")
	private int price;
	
    @OneToOne(mappedBy = "product",cascade=CascadeType.ALL)
	private HibernateOrder order;

	...// getters, setters
	
}

	</pre>

	2. Сервис + имплементация
	
	<pre>
	
package com.samson.hibernate.service;

import java.util.List;
import com.samson.hibernate.model.HibernateCustomer;

public interface CustomerService {
	HibernateCustomer findCustomerById(int id);
    
    void saveCustomer(HibernateCustomer customer);
     
    void deleteCustomerById(int id);
     
    List<HibernateCustomer> findAllCustomers();
}

///////////////////////////////////
package com.samson.hibernate.service;

import java.util.List;
import com.samson.hibernate.model.HibernateOrder;

public interface OrderService {
    HibernateOrder findOrderById(int id);
    
    void saveOrder(HibernateOrder order);
     
    void deleteOrderById(int id);
     
    List<HibernateOrder> findAllOrders();
}

///////////////////////////////////
package com.samson.hibernate.service;

import java.util.List;
import com.samson.hibernate.model.HibernateProduct;

public interface ProductService {
    HibernateProduct findProductById(int id);
    
    void saveProduct(HibernateProduct product);
     
    void deleteProductById(int Id);
     
    List<HibernateProduct> findAllProducts();
}

///////////////////////////////////

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


OrderServiceImpl и ProductServiceImpl аналогично CustomerServiceImpl
	
	</pre>
	
3. JSP

<p>Показать всех клиентов</p>
<table>
	<thead>
  		<tr>
   			<th>id</th>
   			<th>name</th>
   			<th>phone</th>
   			<th>address</th>
   			<th>rating</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allCustomerList}" var="list">
   			<tr>
   				<td><c:out value="${list.customerId}" /></td>
   				<td><c:out value="${list.name}" /></td>
   				<td><c:out value="${list.phone}" /></td>
   				<td><c:out value="${list.address}" /></td>
   				<td><c:out value="${list.rating}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
    
<p>Показать все продукты </p>
<table>
	<thead ="background:#fcf">
  		<tr>
   			<th>id</th>
   			<th>description</th>
   			<th>details</th>
   			<th>price</th>
   			<th colspan="4"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allProductList}" var="list">
   			<tr>
   				<td><c:out value="${list.productId}" /></td>
   				<td><c:out value="${list.description}" /></td>
   				<td><c:out value="${list.details}" /></td>
   				<td><c:out value="${list.price}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>

<p>Показать все заказы </p>
<table>
	<thead >
  		<tr>
   			<th>id</th>
   			<th>date</th>
   			<th>product_id</th>
   			<th>qty</th>
   			<th>amount</th>
   			<th>customer_id</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allOrderList}" var="list">
   			<tr>
   				<td><c:out value="${list.orderId}" /></td>
   				<td><c:out value="${list.date}" /></td>
   				<td><c:out value="${list.product.productId}" /></td>
   				<td><c:out value="${list.qty}" /></td>
   				<td><c:out value="${list.amount}" /></td>
   				<td><c:out value="${list.customer.customerId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>    
    
</div>