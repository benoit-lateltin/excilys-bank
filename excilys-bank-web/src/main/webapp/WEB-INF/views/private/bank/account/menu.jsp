<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="submenu ${bk:if(empty account.cards, 'nocards', '')}">
	<div class="block left">
		<div class="body">
			<c:choose>
				<c:when test="${bk:hasAncestor(page, 'ACCOUNT_OPERATIONS')}">
					<c:set var="active" value="active"/>
				</c:when>
				<c:otherwise>
						<c:set var="active" value=""/>
				</c:otherwise>
			</c:choose>
			<div class="title ${active}">
<%-- 			<div class="title ${bk:if(page.hasAncestor('ACCOUNT_OPERATIONS'), 'active', '')}"> --%>
				<a href="${bk:ctx()}/private/bank/account/${account.number}/operations.html">
					<span class="icon operation left"></span>
					<span class="iconLabel left"><spring:message code="accounts.menu.operations" /></span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<c:if test="${not empty account.cards}">
		<div class="block left">
			<div class="body">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'CARDS')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
					<div class="title ${active}">
<%-- 				<div class="title ${bk:if(page.hasAncestor('CARDS'), 'active', '')}"> --%>
					<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/all/operations.html">
						<span class="icon card left"></span>
						<span class="iconLabel left"><spring:message code="accounts.menu.cards" /></span>
					</a>
					<div class="cb"></div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="block left">
		<div class="body">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'TRANSFERS')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
				<div class="title ${active}">
<%-- 			<div class="title ${bk:if(page.hasAncestor('TRANSFERS'), 'active', '')}"> --%>
				<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
					<span class="icon transfer left"></span>
					<span class="iconLabel left"><spring:message code="accounts.menu.transfers" /></span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<div class="cb"></div>
</div>