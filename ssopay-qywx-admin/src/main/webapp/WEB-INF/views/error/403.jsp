<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>403 - 用户权限不足</title>
</head>

<body>
	<h2 style="padding:10px;">403 - 用户权限不足.</h2>
	<h3 style="padding:10px;">
		<a href="<c:url value="/"/>" target="_parent" style="text-decoration:none; color:#ff0000;">返回首页</a>
		<a href="javascript:history.go(-1);" style="text-decoration:none; color:#ff0000;">返回上一页</a>
	</h3>
</body>
</html>
