<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <t:getAsString name="title" />
        </title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
	<body>
		<div id="header"><t:insertAttribute name="header" /></div>
        <div ><t:insertAttribute name="body" /></div>
        <div id="footer"><t:insertAttribute name="footer" /></div> 
  	</body>
</html>