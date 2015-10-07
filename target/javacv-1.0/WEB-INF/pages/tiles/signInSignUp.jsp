<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib prefix="sec" 
	uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<sec:authorize access="!isAuthenticated()">   
	<div>
		<h3>Некое высказывание</h3>

		<h3>Вы можете залогиниться или зарегестрироваться или ничего не делать</h3>
		
		<form method='GET' action="showPage">
			<input type="submit" name="direction" value="Вход на сайт"/>
			<input type="submit" name="direction" value="Регистрация"/>
		</form>
	</div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">   
	<div>
		<h3>Некое высказывание</h3>
		
		<h3>Здравствуйте,  <sec:authentication property="principal.username" /> </h3>
	</div>
</sec:authorize>