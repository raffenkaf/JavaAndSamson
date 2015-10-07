<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<H2>Технические детали</H2>
<br/><br/>
<p>
	ОС - Ubuntu; IDE - Eclipse; сборка проекта - Maven; <br />
	фронтэнд - JSP, Apach Tiles, HTML/CSS без JavaScript;<br />
	база даных - MySQL; сервер - JbossEWS 2.0 (Tomcat 7.0);<br />
	Spring (MVC, Security, Spring+SQL, Spring+Hibernate)<br />
</p>

<form method="GET" action="showPage">
	<input type="submit" name="direction" value = "Source code, GitHub">
	<H2>Примеры применения фреймворков</H2>
	
	<table>
		<tr>
			<td>	<input name="direction" type="submit" value="Spring+SQL" />		</td>
			<td>	<input name="direction" type="submit" value="Spring+Hibernate"/>	</td>
			<td>	<input name="direction" type="submit" value="Code examples"/>				</td>
		</tr>
	</table>
	
</form>
</div>