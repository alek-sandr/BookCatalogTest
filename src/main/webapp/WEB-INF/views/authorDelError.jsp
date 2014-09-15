<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><spring:message code="label.authorDeletion"/></title>
	<link rel="stylesheet" href="<c:url value="/res/css/bookcatalog.css"/>">
</head>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<a href="<c:url value="/authors"/>"><spring:message code="label.authorsList"/></a>;
	<spring:message code="label.authorDeletion"/>
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<h3><spring:message code="label.unableToDeleteAuthor"/></h3>
	<p><c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/> <spring:message code="label.authorHasCoAuthoredBooks"/>.</p>
	<p><a href="<c:url value="/authors/${author.id}/books"/>"><spring:message code="label.authorBooksList"/></a></p>
</div>

</body>
</html>