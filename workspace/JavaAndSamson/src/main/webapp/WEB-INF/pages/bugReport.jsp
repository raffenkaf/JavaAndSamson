<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h3>Вы нашли баг, и решили сообщить об этом. Спасибо.</h3>
	<form:form method="POST" action="sendBugReport" enctype="multipart/form-data">
		<p>Опишите, пожалуйста, ошибку.</p>
		<p><textarea rows="10" cols="45" name="bugDescriprion"></textarea></p>
		<tr>
			<th><label for="image">Скриншот ошибки(формат - **.jpeg):</label></th>
			<td><input name="image" type="file"/>
		</tr>
				<tr>
			<th><label for="image">Ваш email(не храниться в бд, сразу 
				отправляется на мою почту, raffenkaf@gmail.com):</label></th>
			<td><input type="text" name = "email"/>
		</tr>	
		<p><input type="submit" value="Отправить"></p>
	</form:form>
	
</div>