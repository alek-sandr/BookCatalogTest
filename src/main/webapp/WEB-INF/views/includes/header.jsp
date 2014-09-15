<div class="caption">
    <h2><spring:message code="label.title"/></h2>
</div>

<div class="nav-container">
	<div class="block-right">
		<a href="<c:url value="/logout"/>">
			<spring:message code="label.logout"/>
		</a>
	</div>
	<div>
		<c:forTokens var="element" items="${navElements}" delims=";" varStatus="status">
			${element}
			<c:if test="${!status.last}"> &gt; </c:if>
		</c:forTokens>
	</div>
</div>
