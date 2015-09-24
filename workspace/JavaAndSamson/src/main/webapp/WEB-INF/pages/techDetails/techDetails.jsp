<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<H2>Технические детали</H2>
<br/><br/>
<p>
	ОС - Ubuntu; IDE - Eclipse; сборка проекта - Maven; <br />
	сервер - Tomcat; фронтэнд - JSP, Apach Tiles, HTML/CSS без JavaScript;<br />
	база даных - MySQL; репозиторий+контроль версий - GitHub;<br />
	Spring (MVC, Security, Spring+SQL, Spring+Hibernate, WebFlow)<br />
</p>

<form method="GET" action="showPage">
	<input type="submit" name="direction" value = "Исходники сайта на GitHub">
	<H2>Примеры применения фреймворков</H2>
	
	<table>
		<tr>
			<td>	<input name="direction" type="submit" value="Более детальный осмотр Spring+SQL" />		</td>
			<td>	<input name="direction" type="submit" value="Более детальный осмотр Spring+Hibernate"/>	</td>
			<td>	<input name="direction" type="submit" value="Примеры кода с описанием"/>				</td>
		</tr>
	</table>
	
</form>
</div>