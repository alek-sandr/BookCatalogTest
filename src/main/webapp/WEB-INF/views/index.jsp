<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<c:set var="pageTitle"><spring:message code="label.title"/></c:set>
<%@include file="includes/head.jsp" %>
<body>
<c:set var="navElements">
	<spring:message code="label.mainPage"/>
</c:set>
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

<%@include file="includes/footer.jsp" %>

</body>
</html>