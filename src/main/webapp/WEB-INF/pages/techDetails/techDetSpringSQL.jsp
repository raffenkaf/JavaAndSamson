<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<p>Для каждого пользователя сайта создается 3 таблицы - Products, Customers, Orders.<br/> 
Таблицы создаются в момент перехода на страницу "Технические детали" (если  <br/> 
не были созданы до этого), а удаляются либо после удаления пользователя <br/> 
либо после закрытия сессии.   <br/>
C точки зрения экономии ресурсов хранить 3 таблицы для каждого пользователя <br/>
ОЧЕНЬ неразумно, но я исхожу из того что на сайте будет до 25 активных сессий <br/>
одновременно, а это не критично. <br/>
Содержимое таблиц можно менять. <br/> </p>

<form method="GET" action="showPage">
	<input name="direction" type="submit" value="Change tables">
</form> 

<p> show tables;(фактически, запрос в БД не делаеться) </p>
<table>
	<thead>
  		<tr>
   			<th>Tables_in_SalesDept</th>
   			<th colspan="1"></th>
  		</tr>
 	</thead>
 	<tbody>
		<tr>
			<td>Customers</td>
  		</tr>
  		<tr>
			<td>Products</td>
  		</tr>
  		<tr>
			<td>Orders</td>
  		</tr>
 	</tbody>
</table>
<p> describe Customers; </p><br/>
<table>
	<thead >
  		<tr>
   			<th>Field</th>
   			<th>Type</th>
   			<th>Null</th>
   			<th>Key</th>
   			<th>Default</th>
   			<th>Extra</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listDescribeCustomer}" var="list">
   			<tr>
   				<td><c:out value="${list.field}" /></td>
   				<td><c:out value="${list.type}" /></td>
   				<td><c:out value="${list.isNull}" /></td>
   				<td><c:out value="${list.key}" /></td>
   				<td><c:out value="${list.isDefault}" /></td>
   				<td><c:out value="${list.extra}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> describe Orders; </p><br/>
<table>
	<thead >
  		<tr>
   			<th>Field</th>
   			<th>Type</th>
   			<th>Null</th>
   			<th>Key</th>
   			<th>Default</th>
   			<th>Extra</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listDescribeOrder}" var="list">
   			<tr>
   				<td><c:out value="${list.field}" /></td>
   				<td><c:out value="${list.type}" /></td>
   				<td><c:out value="${list.isNull}" /></td>
   				<td><c:out value="${list.key}" /></td>
   				<td><c:out value="${list.isDefault}" /></td>
   				<td><c:out value="${list.extra}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> describe Products; </p><br/>
<table>
	<thead >
  		<tr>
   			<th>Field</th>
   			<th>Type</th>
   			<th>Null</th>
   			<th>Key</th>
   			<th>Default</th>
   			<th>Extra</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listDescribeProduct}" var="list">
   			<tr>
   				<td><c:out value="${list.field}" /></td>
   				<td><c:out value="${list.type}" /></td>
   				<td><c:out value="${list.isNull}" /></td>
   				<td><c:out value="${list.key}" /></td>
   				<td><c:out value="${list.isDefault}" /></td>
   				<td><c:out value="${list.extra}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> select * from Customers; </p><br/>
<table>
	<thead >
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
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.name}" /></td>
   				<td><c:out value="${list.phone}" /></td>
   				<td><c:out value="${list.address}" /></td>
   				<td><c:out value="${list.rating}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> select * from Products; </p><br/>
<table>
	<thead >
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
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.description}" /></td>
   				<td><c:out value="${list.details}" /></td>
   				<td><c:out value="${list.price}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> select * from Orders; </p><br/>
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
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.date}" /></td>
   				<td><c:out value="${list.productId}" /></td>
   				<td><c:out value="${list.qty}" /></td>
   				<td><c:out value="${list.amount}" /></td>
   				<td><c:out value="${list.customerId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> Разнообразные примеры выборки<br /></p>
<p> SELECT DISTINCT rating/1000 FROM Customers <br/> 
	ORDER BY rating DESC, name </p><br/>
<table>
	<thead >
  		<tr>
   			<th>rating/1000</th>
   			<th colspan="1"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${distinctCustomerList}" var="list">
   			<tr>
   				<td><c:out value="${list.rating/1000}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT name,phone,rating FROM Customers <br/>
    WHERE name LIKE 'ООО%' OR rating>1000 </p><br/>
<table>
	<thead >
  		<tr>
   			<th>name</th>
   			<th>phone</th>
   			<th>rating</th>
   			<th colspan="3"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${likeCustomerList}" var="list">
   			<tr>
   				<td><c:out value="${list.name}" /></td>
   				<td><c:out value="${list.phone}" /></td>
   				<td><c:out value="${list.rating}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT name FROM Customers <br/>
    WHERE id IN <br/>
    (SELECT DISTINCT customer_id FROM Orders); </p><br/>
<table>
	<thead >
  		<tr>
   			<th>name</th>
   			<th colspan="1"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerWithIdIn}" var="list">
   			<tr>
   				<td><c:out value="${list.name}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT * FROM Orders WHERE <br/>
	amount = (SELECT MAX(amount) FROM Orders); </p><br/>
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
 		<tr>
   			<td><c:out value="${selectOrderMaxAmountOrder.id}" /></td>
   			<td><c:out value="${selectOrderMaxAmountOrder.date}" /></td>
   			<td><c:out value="${selectOrderMaxAmountOrder.productId}" /></td>
   			<td><c:out value="${selectOrderMaxAmountOrder.qty}" /></td>
   			<td><c:out value="${selectOrderMaxAmountOrder.amount}" /></td>
   			<td><c:out value="${selectOrderMaxAmountOrder.customerId}" /></td>
  		</tr>
 	</tbody>
</table>
<p> SELECT * FROM Orders <br/>
    WHERE amount = (SELECT MAX(amount) FROM Orders) <br/>
    UNION <br/>
    SELECT * FROM Orders <br/>
    WHERE amount = (SELECT MIN(amount) FROM Orders); </p><br/>
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
 		<c:forEach items="${selectOrderMaxMinAmountOrder}" var="list">
   			<tr>
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.date}" /></td>
   				<td><c:out value="${list.productId}" /></td>
   				<td><c:out value="${list.qty}" /></td>
   				<td><c:out value="${list.amount}" /></td>
   				<td><c:out value="${list.customerId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT null is null, null = null;</p><br/>
<table>
	<thead >
  		<tr>
   			<th>null is null</th>
   			<th>null = null</th>
   			<th colspan="2"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<tr>
   			<td>1</td>
   			<td>null</td>
   		</tr>
 	</tbody>
</table>
<p> Проверяет каждую строчку товаров в заказах</p>
<p> SELECT * FROM Products <br/>
    WHERE EXISTS <br/>
    (SELECT * FROM Orders <br/>
    WHERE product_id = Products.id); </p><br/>
<table>
	<thead >
  		<tr>
   			<th>id</th>
   			<th>description</th>
   			<th>details</th>
   			<th>price</th>
   			<th colspan="4"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerExist}" var="list">
   			<tr>
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.description}" /></td>
   				<td><c:out value="${list.details}" /></td>
   				<td><c:out value="${list.price}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
Проверяет наличие клиентов, заказавших все товары. <br/>
<p> SELECT * FROM Customers WHERE NOT EXISTS <br/>
    	(SELECT * FROM Products WHERE NOT EXISTS <br/>
			(SELECT * FROM Orders <br/>
        		WHERE product_id = Products.id)); </p><br/>
<table>
	<thead >
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
 		<c:if test="${not empty var1}">
			<c:forEach items="${allCustomerList}" var="list">
   				<tr>
   					<td><c:out value="${list.id}" /></td>
   					<td><c:out value="${list.name}" /></td>
   					<td><c:out value="${list.phone}" /></td>
   					<td><c:out value="${list.address}" /></td>
   					<td><c:out value="${list.rating}" /></td>
   				</tr>
 			</c:forEach>
 		</c:if>
 	</tbody>
</table>
<p> SELECT L.name as LName,R.name as RName <br/>
	FROM Customers L, Customers R <br/>
	WHERE L.rating = R.rating <br/>
	AND L.name &lt R.name; </p><br/>
	<table>
	<thead >
  		<tr>
   			<th>LName</th>
   			<th>RName</th>
   			<th colspan="2"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listCustomerWithAliases}" var="list">
   			<tr>
   				<td><c:out value="${list.name}" /></td>
   				<td><c:out value="${list.otherCustomerName}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT date,customer_id,amount, <br/>
    CASE <br/>
    WHEN amount &lt = 5000 THEN 'Малый' <br/>
    WHEN amount BETWEEN 5000 AND 15000 THEN 'Средний' <br/>
    WHEN amount &gt 15000 THEN 'Крупный' <br/>
    END as size<br/> 
    FROM Orders</p><br/>
<table>
	<thead >
  		<tr>
   			<th>date</th>
   			<th>customer_id</th>
   			<th>amount</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listOrderCaseSelect}" var="list">
   			<tr>
   				<td><c:out value="${list.date}" /></td>
   				<td><c:out value="${list.customerId}" /></td>
   				<td><c:out value="${list.amount}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT customer_id, SUM(amount) FROM Orders <br/>
    GROUP BY customer_id <br/>
    HAVING SUM(amount) &gt 20000; </p><br/>
<table>
	<thead>
  		<tr>
   			<th>customer_id</th>
   			<th>SUM(amount)</th>
   			<th colspan="2"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listOrderHavingSelect}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.sum}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p> SELECT customer_id, SUM(amount) FROM Orders <br/>
	GROUP BY customer_id WITH ROLLUP; </p><br/>
<table>
	<thead >
  		<tr>
   			<th>customer_id</th>
   			<th>SUM(amount)</th>
   			<th colspan="2"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${listOrderSelectHavingWithRollup}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.sum}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>

Каждый с каждым(декартово произведение)
<p> SELECT c.id as customerId, name, o.id as orderId, product_id <br/> 
	FROM Customers as c CROSS JOIN Orders as o </p><br/>
<table>
	<thead >
  		<tr>
   			<th>customerId</th>
   			<th>name</th>
   			<th>orderId</th>
   			<th>product_id</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerOrderCrossJoin}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.customerName}" /></td>
  				<td><c:out value="${list.orderId}" /></td>
  				<td><c:out value="${list.orderProductId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
Имеет смысл только если в разных таблицах есть столбцы с одинаковыми именами и типами данных
<p> SELECT c.id as customerId, name, o.id as orderId, product_id
 	FROM Customers c NATURAL JOIN Orders o; </p><br/>
<table>
	<thead >
  		<tr>
   			<th>customerId</th>
   			<th>name</th>
   			<th>orderId</th>
   			<th>product_id</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerOrderNaturalJoin}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.customerName}" /></td>
  				<td><c:out value="${list.orderId}" /></td>
  				<td><c:out value="${list.orderProductId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
Аналог NATURAL JOIN без привязки к типам данных и явным указанием сравниваемых стобцов
<p> SELECT c.id as customerId, name, o.id as orderId, product_id
	FROM Customers as c JOIN Orders as o USING (id); </p><br/>
<table>
	<thead >
  		<tr>
   			<th>customerId</th>
   			<th>name</th>
   			<th>orderId</th>
   			<th>product_id</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerOrderJoinUseId}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.customerName}" /></td>
  				<td><c:out value="${list.orderId}" /></td>
  				<td><c:out value="${list.orderProductId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>

<p> SELECT c.id as customerId, name, o.id as orderId, product_id
	FROM Customers as c JOIN Orders as o ON(c.id = o.customer_id); </p><br/>
<table>
	<thead >
  		<tr>
   			<th>customerId</th>
   			<th>name</th>
   			<th>orderId</th>
   			<th>product_id</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${selectCustomerOrderJoinOn}" var="list">
   			<tr>
  				<td><c:out value="${list.customerId}" /></td>
  				<td><c:out value="${list.customerName}" /></td>
  				<td><c:out value="${list.orderId}" /></td>
  				<td><c:out value="${list.orderProductId}" /></td>
  			</tr>
 		</c:forEach>
 	</tbody>
</table>
</div>