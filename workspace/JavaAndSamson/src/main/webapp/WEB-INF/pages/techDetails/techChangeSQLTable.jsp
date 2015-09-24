<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
 
<form:form method="POST" action="deleteRecord">
<p> select * from Customers; </p><br/>
<table>
	<thead style="background:#fcf">
  		<tr>
  			<th></th>
   			<th>id</th>
   			<th>name</th>
   			<th>phone</th>
   			<th>address</th>
   			<th>rating</th>
   			<th colspan="6"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allCustomerList}" var="list">
 			<tr>
 				<th><input type="checkbox" name="customerCheckBox" 
 					value="${list.id}"/></th>
   				<td><c:out value="${list.id}" /></td>
   				<td><c:out value="${list.name}" /></td>
   				<td><c:out value="${list.phone}" /></td>
   				<td><c:out value="${list.address}" /></td>
   				<td><c:out value="${list.rating}" /></td>
   			</tr>
 		</c:forEach>
 	</tbody>
</table>
<p><input type="submit" value="Delete"/></p>

<p> select * from Products; </p><br/>
<table>
	<thead style="background:#fcf">
	
  		<tr>
  			<th></th>
   			<th>id</th>
   			<th>description</th>
   			<th>details</th>
   			<th>price</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allProductList}" var="list">
 		<tr>
 			<th><input type="checkbox" name="productCheckBox" 
 				value="${list.id}"/></th>
   			<td><c:out value="${list.id}" /></td>
   			<td><c:out value="${list.description}" /></td>
   			<td><c:out value="${list.details}" /></td>
   			<td><c:out value="${list.price}" /></td>
   		</tr>
   		</c:forEach>
 	</tbody>
</table>
<p><input type="submit" value="Delete"/></p>

<p> select * from Orders; </p><br/>
<table>
	<thead style="background:#fcf">
  		<tr>
  			<th></th>
   			<th>id</th>
   			<th>date</th>
   			<th>product_id</th>
   			<th>qty</th>
   			<th>amount</th>
   			<th>customer_id</th>
   			<th colspan="7"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<c:forEach items="${allOrderList}" var="list">
   			<tr>
   				<th><input type="checkbox" name="orderCheckBox" 
 					value="${list.id}"/></th>
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
<p><input type="submit" value="Delete"/></p>
</form:form>
 
<!-- ///////////////////////////////////////////////////////////////////////////////////////////////// -->

<form:form method="POST" action="addRecord" modelAttribute="customer">
<p>Добавить запись в таблицу Customers</p>
<table>
	<thead style="background:#fcf">
  		<tr>
   			<th>name</th>
   			<th>phone</th>
   			<th>address</th>
   			<th>rating</th>
   			<th colspan="4"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<tr>
   			<td><form:input path="name"/></td>
   			<td><form:input path="phone"/></td>
   			<td><form:input path="address"/></td>
   			<td><form:input path="rating"/></td>
   		</tr>
 	</tbody>
</table>
<p><input type="submit" value="Add"/></p>
</form:form>
<form:form method="POST" action="addRecord" modelAttribute="product">
<p>Добавить запись в таблицу Products</p>
<table>
	<thead style="background:#fcf">
  		<tr>
   			<th>description</th>
   			<th>details</th>
   			<th>price</th>
   			<th colspan="3"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<tr>
   			<td><form:input path="description"/></td>
   			<td><form:input path="details"/></td>
   			<td><form:input path="price"/></td>
   		</tr>
 	</tbody>
</table>
<p><input type="submit" value="Add"/></p>
</form:form>
<form:form method="POST" action="addRecord" modelAttribute="order">
<p> select * from Orders; </p><br/>
<table>
	<thead style="background:#fcf">
  		<tr>
   			<th>date</th>
   			<th>product_id</th>
   			<th>qty</th>
   			<th>amount</th>
   			<th>customer_id</th>
   			<th colspan="5"></th>
  		</tr>
 	</thead>
 	<tbody>
 		<tr>
   			<td><form:input path="date"/></td>
   			<td><form:input path="productId"/></td>
   			<td><form:input path="qty"/></td>
   			<td><form:input path="amount"/></td>
   			<td><form:input path="customerId"/></td>
  		</tr>
 	</tbody>
</table>
<p><input type="submit" value="Add"/></p>

</form:form>

</div>