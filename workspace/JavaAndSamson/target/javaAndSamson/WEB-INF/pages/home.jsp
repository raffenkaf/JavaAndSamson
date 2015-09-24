<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h1>Это ваша домашняя страница, здесь вы можете удалить свой профиль</h1>
	<form action="deleteUserProfile" method="POST">
		<input type= "submit" value="Удалить пользователя" /> 
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> 
	</form> 
</div>