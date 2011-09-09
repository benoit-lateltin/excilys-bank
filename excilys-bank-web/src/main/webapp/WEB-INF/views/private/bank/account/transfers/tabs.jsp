<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul class="tabs">
	<c:choose>
		<c:when test="${bk:hasAncestor(page, 'TRANSFER_OPERATIONS')}">
			<c:set var="active" value="active"/>
		</c:when>
		<c:otherwise>
				<c:set var="active" value=""/>
		</c:otherwise>
	</c:choose>
<%-- 	<li class="${bk:if(page.hasAncestor('TRANSFER_OPERATIONS'), 'active', '')}"> --%>
	<li class="${active}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
			<spring:message code="transfers.menu.operations" />
		</a>
	</li>
	<c:choose>
		<c:when test="${bk:hasAncestor(page, 'TRANSFER_PERFORM')}">
			<c:set var="active" value="active"/>
		</c:when>
		<c:otherwise>
				<c:set var="active" value=""/>
		</c:otherwise>
	</c:choose>
<%-- 	<li class="${bk:if(page.hasAncestor('TRANSFER_PERFORM'), 'active', '')}"> --%>
	<li class="${active}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/perform.html">
			<spring:message code="transfers.menu.perform" />
		</a>
	</li>
</ul>