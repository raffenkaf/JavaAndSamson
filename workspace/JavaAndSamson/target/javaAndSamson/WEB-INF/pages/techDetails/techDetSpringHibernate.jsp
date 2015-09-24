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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "address")
	private float rating;

	...// getters, setters

}
///////////////////////////////////	
package com.samson.hibernate.model;

import java.sql.Timestamp;

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
@Table(name = "Orders")
public class Order {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Timestamp date;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(name = "amount")
	private int amount;
	
	private int productId;
	
	private int customerId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
		name = "productId", 
		table = "Products",
		referencedColumnName = "id"
	)
	public int getProductId() {
		return productId;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
		name = "customerId", 
		table = "Customers",
		referencedColumnName = "id"
	public int getCustomerId() {
		return productId;
	}

	...// getters, setters

}
///////////////////////////////////
package com.samson.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "price")
	private int price;

	...// getters, setters
	
}

	</pre>

	2. Дао + имплементация
	
	<pre>
	
package com.samson.hibernate.dao;

import java.util.List;

import com.samson.hibernate.model.Customer;

public interface CustomerDAO {
    Customer findCustomerById(int id);
    
    void saveCustomer(Customer customer);
     
    void deleteCustomerById(int id);
     
    List &ltCustomer&gt findAllCustomers();
 
    Customer findCustomerByLogin(String login);
}
///////////////////////////////////
package com.samson.hibernate.dao;

import java.util.List;

import com.samson.hibernate.model.Order;

public interface OrderDAO {
    Order findOrderById(int id);
    
    void saveOrder(Order order);
     
    void deleteOrderById(int id);
     
    List &ltOrder&gt findAllOrders();
}
///////////////////////////////////
package com.samson.hibernate.dao;

import java.util.List;

import com.samson.hibernate.model.Product;

public interface ProductDAO {
    Product findProductById(int id);
    
    void saveProduct(Product product);
     
    void deleteProductById(int Id);
     
    List &ltProduct&gt findAllProducts();
}
///////////////////////////////////

package com.samson.hibernate.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.samson.hibernate.model.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Customer findCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Customer)session.get(Customer.class, id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(customer);
	}

	@Override
	public void deleteCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer)session.get(Customer.class, id);
		session.delete(customer);
	}

	@Override
	public List &ltCustomer&gt  findAllCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM  Customers");
		return query.list();
	}

	@Override
	public Customer findCustomerByLogin(String login) {
		Query query= sessionFactory.getCurrentSession().
		        createQuery("from Customers where login=:login");
		query.setParameter("login", login);
		Customer customer = (Customer) query.uniqueResult();
		return customer;
	}
	
}

ProductDAOImpl и OrderDAOImpl аналогично CustomerDAOImpl
	
	</pre>
	
3. JSP

<p>Показать всех клиентов</p>
<table>
	<thead style="background:#fcf">
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
	<thead style="background:#fcf">
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
	<thead style="background:#fcf">
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