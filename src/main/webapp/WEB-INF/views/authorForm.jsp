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
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/res/css/bookcatalog.css"/>">
</head>
<body>

<c:set var="navElements">
	<a href="<c:url value="/"/>"><spring:message code="label.mainPage"/></a>;
	<a href="<c:url value="/authors"/>"><spring:message code="label.authorsList"/></a>;
	${title}
</c:set>
<%@include file="includes/header.jsp" %>

<div class="container">
	<form action="${actionPath}" method="post">
		<p><strong><spring:message code="label.fillFieldsAndPressButton"/></strong>
			<button type="submit">${buttonText}</button>
		</p>
		<div class="form-group">
			<label class="left-label"><spring:message code="label.lastName"/>:</label>
			<input type="text" name="lastName" value="<c:out value="${author.lastName}"/>" required>
		</div>
		<div class="form-group">
			<label class="left-label"><spring:message code="label.firstName"/>:</label>
			<input type="text" name="firstName" value="<c:out value="${author.firstName}"/>" required>
		</div>
		<input type="hidden" name="id" value="${author.id}">
	</form>
	<spring:hasBindErrors name="author">
	<div class="err-container">
		<h3><spring:message code="label.fixErrors"></spring:message></h3>
		<c:forEach var="error" items="${errors.allErrors}">
			<p>
				<spring:message code="label.field"></spring:message> 
				<spring:message code="author.${error.field}"></spring:message>:
				${error.defaultMessage}
			</p>
		</c:forEach>
	</div>
	</spring:hasBindErrors>
</div>

</body>
</html>