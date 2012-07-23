<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.SnsOa.system.util.SystemSessionAttreName"%>
<%@include file="/include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
	<%
		String context = request.getContextPath();
		boolean isLogin=request.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)==null;
	%>
<html>
<head>
	<title>Home</title>
		<link rel="stylesheet" type="text/css"
			href="<%=context%>/resources/js/ext/resources/css/ext-all.css" />
		<script type="text/javascript"
			src="<%=context%>/resources/js/ext/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=context%>/resources/js/jquery-1.6.3.js"></script>
		<% if(isLogin){%>
			<script type="text/javascript" src="<%=context %>/resources/js/Ext_APP/login/login.js"></script>
		<%} %>
</head>
	<body>
		<%=isLogin %>
	</body>
</html>
