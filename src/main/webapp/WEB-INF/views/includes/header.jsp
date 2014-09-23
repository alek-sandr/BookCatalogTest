<div class="container">
    <div class="caption">
        <strong><spring:message code="label.title"/></strong>
    </div>
	<ol class="breadcrumb">
		<c:forTokens var="element" items="${navElements}" delims=";" varStatus="status">
			<li <c:if test="${status.last}"> class="active"</c:if>>${element}</li>
		</c:forTokens>
		<span class="pull-right"><a href="<c:url value="/logout"/>"><spring:message code="label.logout"/></a></span>
    </ol>
</div>
