<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<c:set var="pageTitle"><spring:message code="label.authorsList"/></c:set>
<%@include file="includes/head.jsp" %>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<spring:message code="label.authorsList"/>
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<div>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<a class="pull-right" href="<c:url value="/authors/add"/>">
			<button class="btn btn-success btn-sm"><spring:message code="label.createAuthor"/></button>
		</a>
		</sec:authorize>
		<h3><spring:message code="label.authorsList"/></h3>
	</div>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code="label.lastName"/></th>
			<th><spring:message code="label.firstName"/></th>
			<th class="actions"><spring:message code="label.actions"/></th>
		</tr>
		<c:forEach var="author" items="${authorsList}">
            <tr id="${author.id}">
				<td><c:out value="${author.lastName}"/></td>
				<td><c:out value="${author.firstName}"/></td>
				<td>
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<a href="<c:url value="/authors/edit/${author.id}"/>"><spring:message code="label.modify"/></a>
					<a class="delete_author" href="<c:url value="/authors/del/${author.id}"/>"><spring:message code="label.remove"/></a>
					</sec:authorize>
					<a href="<c:url value="/authors/${author.id}/books"/>"><spring:message code="label.booksList"/></a>
				</td>
            </tr>
        </c:forEach>
	</table>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>