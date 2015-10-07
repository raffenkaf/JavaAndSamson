<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <t:getAsString name="title" />
        </title>
        <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    </head>
	<body>
		<t:insertAttribute name="header" />
        <t:insertAttribute name="body" />
        <t:insertAttribute name="footer" />
  	</body>
</html>