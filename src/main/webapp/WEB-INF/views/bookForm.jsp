<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${!empty edit}">
		<c:set var="actionPath"><c:url value="/books/edit/${book.id}"/></c:set>
		<c:set var="buttonText"><spring:message code="label.apply"/></c:set>
		<c:set var="title"><spring:message code="label.bookModification"/></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="actionPath"><c:url value="/books/add"/></c:set>
		<c:set var="buttonText"><spring:message code="label.create"/></c:set>
		<c:set var="title"><spring:message code="label.bookCreation"/></c:set>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/res/css/bookcatalog.css"/>">
</head>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<a href="<c:url value="/books"/>"><spring:message code="label.booksList"/></a>;
	${title}
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<form action="${actionPath}" method="post">
		<p><strong><spring:message code="label.fillFieldsAndPressButton"/></strong>
			<button type="submit"><c:out value="${buttonText}"/></button>
		</p>
		<div class="form-group">
			<label class="left-label"><spring:message code="book.title"/>:</label>
			<input type="text" name="title" value="<c:out value="${book.title}"/>" required>
		</div>
		<div class="form-group">
			<label class="left-label"><spring:message code="book.description"/>:</label>
			<textarea rows="7" type="text" name="description" required><c:out value="${book.description}"/></textarea>
		</div>
		<div class="form-group">
			<label class="left-label"><spring:message code="book.year"/>:</label>
			<input type="text" name="year" value="<c:out value="${book.year}"/>" required>
		</div>
		<div class="form-group">
			<label class="left-label"><spring:message code="book.authors"/>:</label>
			<select size="6" multiple name="authors" required>
				<c:forEach var="author" items="${allAuthors}">
					<option
					<c:forEach var="id" items="${selectedIds}">
						<c:if test="${id eq author.id}">selected</c:if>
					</c:forEach>
					value="${author.id}"><c:out value="${author.lastName}"/>, <c:out value="${author.firstName}"/></option>
				</c:forEach>
			</select>
		</div>
		<input type="hidden" name="id" value="${book.id}">
	</form>
	<spring:hasBindErrors name="book">
	<div class="err-container">
		<h3><spring:message code="label.fixErrors"></spring:message></h3>
		<c:forEach var="error" items="${errors.allErrors}">
			<p>
				<spring:message code="label.field"></spring:message> 
				<spring:message code="book.${error.field}"></spring:message>:
				${error.defaultMessage}
			</p>
		</c:forEach>
	</div>
	</spring:hasBindErrors>
</div>

</body>
</html>