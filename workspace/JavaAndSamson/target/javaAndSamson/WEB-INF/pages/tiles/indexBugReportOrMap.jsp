<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
<form method="GET" action="showPage">
	<table>
    	<tr> 
    		<td>
    			<input type="submit" name="direction" 
    			value="Главная страница"/>     				
    		</td> 
			<td>
				<input type="submit" name="direction" 
				value="Карта сайта"/>				
			</td> 
		</tr>
	</table>  
</form>
</div>