<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><spring:message code="label.title"/></title>
	<link rel="stylesheet" href="<c:url value="/res/css/bookcatalog.css"/>">
</head>
<body>
<%@include file="includes/header.jsp" %>

<div class="container">
	<p>
		<a href="<c:url value="/books"/>">
			<spring:message code="label.booksList"/>
		</a>
	</p>
	<p>
		<a href="<c:url value="/authors"/>">
			<spring:message code="label.authorsList"/>
		</a>
	</p>
</div>

</body>
</html>