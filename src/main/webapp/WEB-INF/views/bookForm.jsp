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
<c:set var="pageTitle">${title}</c:set>
<%@include file="includes/head.jsp" %>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<a href="<c:url value="/books"/>"><spring:message code="label.booksList"/></a>;
	${title}
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<h3>${title}</h3>
	<br/>
	<spring:hasBindErrors name="book">
	<div class="alert alert-danger alert-dismissible" role="alert">
    	<button type="button" class="close" data-dismiss="alert">
    	    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
    	</button>
		<strong><spring:message code="label.fixErrors"></spring:message></strong><br/>
		<c:forEach var="error" items="${errors.allErrors}">
			<p>
				<spring:message code="label.field"></spring:message> 
				<spring:message code="book.${error.field}"></spring:message>:
				${error.defaultMessage}
			</p>
		</c:forEach>
	</div>
	</spring:hasBindErrors>
	<form class="form-horizontal" action="${actionPath}" method="post">
		<div class="form-group form-group-sm">
			<label class="control-label col-sm-2"><spring:message code="book.title"/>:</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="title" value="<c:out value="${book.title}"/>" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2"><spring:message code="book.description"/>:</label>
			<div class="col-sm-9">
				<textarea class="form-control" rows="5" type="text" name="description" required><c:out value="${book.description}"/></textarea>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="control-label col-sm-2"><spring:message code="book.year"/>:</label>
			<div class="col-sm-9">
				<input class="form-control" type="number" name="year" value="<c:out value="${book.year}"/>" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2"><spring:message code="book.authors"/>:</label>
			<div class="col-sm-9">
				<select class="form-control" size="6" multiple name="authors" required>
				<c:forEach var="author" items="${allAuthors}">
					<option
					<c:forEach var="bookAuthor" items="${book.authors}">
						<c:if test="${bookAuthor.id eq author.id}">selected</c:if>
					</c:forEach>
					value="${author.id}"><c:out value="${author.lastName}"/>, <c:out value="${author.firstName}"/></option>
				</c:forEach>
				</select>
			</div>
		</div>
		<!--<label><spring:message code="label.fillFieldsAndPressButton"/></label>-->
		<div class="form-group form-group-sm">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit"><c:out value="${buttonText}"/></button>
			</div>
		</div>
		<input type="hidden" name="id" value="${book.id}">
	</form>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>