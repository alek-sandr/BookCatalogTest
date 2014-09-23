<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${!empty edit}">
		<c:set var="actionPath"><c:url value="/authors/edit/${author.id}"/></c:set>
		<c:set var="buttonText"><spring:message code="label.apply"/></c:set>
		<c:set var="title"><spring:message code="label.authorModification"/></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="actionPath"><c:url value="/authors/add"/></c:set>
		<c:set var="buttonText"><spring:message code="label.create"/></c:set>
		<c:set var="title"><spring:message code="label.authorCreation"/></c:set>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<c:set var="pageTitle">${title}</c:set>
<%@include file="includes/head.jsp" %>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<a href="<c:url value="/authors"/>"><spring:message code="label.authorsList"/></a>;
	${title}
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<h3>${title}</h3>
	<br/>
	<spring:hasBindErrors name="author">
	<div class="alert alert-danger alert-dismissible" role="alert">
	    <button type="button" class="close" data-dismiss="alert">
	        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	    </button>
		<strong><spring:message code="label.fixErrors"></spring:message></strong><br/>
		<c:forEach var="error" items="${errors.allErrors}">
			<p>
				<spring:message code="label.field"></spring:message> 
				<spring:message code="author.${error.field}"></spring:message>:
				${error.defaultMessage}
			</p>
		</c:forEach>
	</div>
	</spring:hasBindErrors>
	<form class="form-horizontal" action="${actionPath}" method="post">
		<div class="form-group form-group-sm">
			<label class="control-label col-sm-2"><spring:message code="label.lastName"/>:</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="lastName" value="<c:out value="${author.lastName}"/>" required>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="control-label col-sm-2"><spring:message code="label.firstName"/>:</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="firstName" value="<c:out value="${author.firstName}"/>" required>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary" type="submit">${buttonText}</button>
			</div>
		</div>
		<input type="hidden" name="id" value="${author.id}">
	</form>
</div>

<%@include file="includes/footer.jsp" %>

</body>
</html>