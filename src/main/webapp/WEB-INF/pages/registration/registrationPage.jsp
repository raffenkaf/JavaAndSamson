<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<h2>Создание аккаунта</h2>

	<form:form action="addUserProfile" method="POST" 
			modelAttribute="userProfile" >				
		<fieldset>												
		
		<table cellspacing="0">
			<tr>
				<th><label for="name">Имя:</label></th>
				<td><form:input path="name" size="15" id="name"/><br/>
					<form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="userName">Логин:</label></th>
				<td><form:input path="userName" size="15" maxlength="15" 
					id="userName"/> 									
					<small id="userNameMsg">Без пробелов, пожалуйста.</small><br/>
					<form:errors path="userName" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th><label for="userPassword">Пароль:</label></th>
				<td><form:password path="password" size="30"
					showPassword="true" id="userPassword"/> 			
					<small>6 букв или больше</small><br/>
					<form:errors path="password" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th></th>
				<td><input name="commit" type="submit"
					value="Создать аккаунт" /></td>
			</tr>
		</table>
		
	</fieldset>
	
</form:form>
</div>