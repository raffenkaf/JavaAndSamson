<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<img src="<c:url value='/resources/images/MyPhoto.jpg' />" 
	alt="Здесь должно быть мое фото, но, видать, баг"/>
<br/><br/>
<table>
	<tr>
		<th>Имя</th>
		<th>Самсон Сергей Олександрович</th>
	</tr>
	<tr>
		<th>Возраст</th>
		<th>24 года(1991 год рождения)</th>
	</tr>
	<tr>
		<th>Образование</th>
		<th>Специалист - НТУУ "КПИ" 2008 - 2014<br/>
		    Факультет электроники, Кафедра электронных приборов и устройств</th>
	</tr>
	<tr>
		<th>Опыт работы</th>
		<th>Оператор колл-центра(08,2014 - 01,2015) - "УКГ"<br/>
		    Оператор колл-центра(02,2014 - текущее время) - "Укртелеком"</th>
	</tr>
	<tr>
		<th>Знание технологий по 10-й шкале<br/>
		    (1 – общие знания; <br/>
		    2 – ознакомлен с литературой;<br/> 
		    3 – есть небольшой проект; <br/>
		    4 – есть несколько проектов; <br/>
		    5 – 2 года опыта работы; …; <br/>
		    9 – определяет стратегию развития; <br/> 
		    10 – один из авторов)
		<th>
			<table>
				<tr>
					<th>Java Core</th>
					<th>3.5</th>
				</tr>
				<tr>
					<th>MySQL</th>
					<th>2.5</th>
				</tr>
				<tr>
					<th>Spring</th>
					<th>3.0</th>
				</tr>
				<tr>
					<th>Git</th>
					<th>3.0</th>
				</tr>
				<tr>
					<th>Eclipse</th>
					<th>3.0</th>
				</tr>
				<tr>
					<th>Hibarnate</th>
					<th>2.5</th>
				</tr>	
				<tr>
					<th>HTML/CSS</th>
					<th>3.0</th>
				</tr>
				<tr>
					<th>Maven</th>
					<th>3.0</th>
				</tr>
				<tr>
					<th>Tomcat</th>
					<th>3.0</th>
				</tr>
			</table></th>
		</tr>
	<tr>
		<th>Языки</th>
		<th>Українська - розмовна, русский - разговорный, Eglish - pre intermediate</th>
	</tr>
	<tr>
		<th>Контактные данные</th>
		<th>н.т. - 380661607949 email - raffenkaf@gmail.com</th>
	</tr>
	<tr>
		<th>Закачать СV </th>
		<th>
			<form action="downloadFile" method = "GET">
				<input type ="submit" value="В формате PDF" />
			</form>
		</th>
	</tr>
</table>
</div>