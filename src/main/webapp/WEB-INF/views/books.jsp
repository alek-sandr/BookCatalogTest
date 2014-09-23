<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:choose>
	<c:when test="${!empty author}">
		<c:set var="title"><spring:message code="label.authorBooksList"/></c:set>
		<c:set var="navElement"><a href="<c:url value="/authors"/>"><spring:message code="label.authorsList"/></a></c:set>
	</c:when>
	<c:when test="${!empty search}">
		<c:set var="title"><spring:message code="label.searchResult"/></c:set>
		<c:set var="navElement"><a href="<c:url value="/books"/>"><spring:message code="label.booksList"/></a></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="showSearch" value="true"></c:set>
		<c:set var="showAdd" value="true"></c:set>
		<c:set var="title"><spring:message code="label.booksList"/></c:set>
		<c:set var="navElement"><spring:message code="label.booksList"/></c:set>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<c:set var="pageTitle">${title}</c:set>
<%@include file="includes/head.jsp" %>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	${navElement}
	<c:if test="${!empty author || !empty search}">;${title}</c:if>
</c:set>
<%@include file="includes/header.jsp" %>

<c:if test="${!empty showSearch}">
	<div class="container">
		<form class="form-horizontal" role="form" action="<c:url value="/books/search"/>" method="get">
			<div class="input-group input-group-sm">
				<span class="input-group-addon"><spring:message code="label.enterBookTitle"/></span>
				<input class="form-control" type="search" name="query" required>
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit"><spring:message code="label.searchBook"/></button>
				</span>
			</div>
	  		</form>
	</div>
	</br>
</c:if>

<div class="container">
	<div>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<c:if test="${!empty showAdd}">
				<a class="pull-right" href="<c:url value="/books/add"/>">
					<button class="btn btn-success btn-sm"><spring:message code="label.createBook"/></button>
				</a>
			</c:if>
		</sec:authorize>
	<h3><spring:message code="label.booksList"/></h3>
	</div>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th><spring:message code="label.bookTitle"/></th>
			<th><spring:message code="label.shortDescription"/></th>
			<th><spring:message code="label.year"/></th>
			<th><spring:message code="label.authors"/></th>
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<th class="actions"><spring:message code="label.actions"/></th>
			</sec:authorize>
		</tr>
		<c:forEach var="book" items="${booksList}">
            <tr id="${book.id}">
				<td><c:out value="${book.title}"/></td>
				<td><c:out value="${book.description}"/></td>
				<td><c:out value="${book.year}"/></td>
				<td>
					<c:forEach var="author" items="${book.authors}">
						<c:out value="${author}"/><br/>
					</c:forEach>
				</td>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<td>
					<a href="<c:url value="/books/edit/${book.id}"/>"><spring:message code="label.modify"/></a>
					<a class="delete_book" href="<c:url value="/books/del/${book.id}"/>"><spring:message code="label.remove"/></a>
				</td>
				</sec:authorize>
            </tr>
        </c:forEach>
	</table>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>