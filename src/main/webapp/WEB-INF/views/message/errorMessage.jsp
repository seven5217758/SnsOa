<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/include.jsp" %>
<%@page import="com.SnsOa.system.util.SystemSessionAttreName"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
	<%
		String context = request.getContextPath();
		String Msg=(String)request.getAttribute(SystemSessionAttreName.MESSAGE_ERROR);
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'errorMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%=Msg %>
  </body>
</html>
