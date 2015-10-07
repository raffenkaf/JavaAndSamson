<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib prefix="sec" 
	uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<sec:authorize access="!isAuthenticated()" >   
	<div>
		<h3>Некое высказывание</h3>

		<h3>Вы можете залогиниться или зарегестрироваться или ничего не делать</h3>

		<form name='loginform'
			action="<c:url value='/login' />" method='POST'>
			
			<table>
				<tr>
					<td>Логин:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Sign In" /></td>
				</tr> 
<!--			<tr>
					<td><label for="remember_me" class="inline">Remember me</label><td/>
					<td><input id="remember_me" name="_spring_security_remember_me"
						type="checkbox"/><td/>
				</tr>
--> 
			</table>
			
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		
		<form method='GET' action="showPage">
			<input type="submit" name="direction" value="Регистрация"/>
		</form>
	</div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">   
	<div>
		<h3>Некое высказывание</h3>
		
		<h3>Здравствуйте,  <sec:authentication property="principal.username" /> </h3>
		<form action="<c:url value="/logout" />" method="post">
			<input type= "submit" value="Logout" /> 
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<form action="showPage" method="GET">
			<input name="direction" type= "submit" value="Домашняя страница" /> 
		</form>
	</div>
</sec:authorize>