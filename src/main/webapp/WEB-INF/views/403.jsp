<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<c:set var="pageTitle"><spring:message code="label.accessDenied"/></c:set>
<%@include file="includes/head.jsp" %>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<h1><spring:message code="label.accessDenied"/></h1>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>