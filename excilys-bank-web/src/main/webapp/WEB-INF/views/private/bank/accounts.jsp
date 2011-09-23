<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<h1>
	<span class="icon bank"></span> <spring:message code="accounts.welcome" />
</h1>
<c:if test="${not empty checkingAccounts}">
	<table class="accounts striped">
		<thead>
			<tr>
				<td class="ico">
					<span class="icon checking"></span>
				</td>
				<td colspan="4"><spring:message code="accounts.checkings" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${checkingAccounts}" var="account">
				<tr>
					<td></td>
					<td colspan="2" class="accountName">
						<a href="${bk:ctx()}/private/bank/account/${account.number}/operations.html">
							${account.type.id}
						</a>
					</td>
					<td class="number">${account.number}</td>
					<td class="amount">${bk:amount(account.balance)}<spring:message code="common.euro" /></td>
				</tr>
				<c:forEach items="${account.cards}" var="card">
					<tr>
						<td></td>
						<td class="ico">
							<span class="icon card"></span>
						</td>
						<td class="cardName">
							<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${card.number}/operations.html">
								${card.type.id}
							</a>
						</td>
						<td class="number">${card.number}</td>
						<td class="amount">${bk:amount(card.pending)}<spring:message code="common.euro" /></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<c:if test="${not empty savingAccounts}">
	<table class="accounts striped">
		<thead>
			<tr>
				<td class="ico">
					<span class="icon saving"></span>
				</td>
				<td colspan="3"><spring:message code="accounts.savings" /></td>
			</tr>
		</thead>
		<tbody>
				<c:forEach items="${savingAccounts}" var="account">
					<tr>
						<td></td>
						<td colspan="2" class="accountName">
							<a href="${bk:ctx()}/private/bank/account/${account.number}/operations.html">
								${account.type.id}
							</a>
						</td>
						<td class="number">${account.number}</td>
						<td class="amount">${bk:amount(account.balance)}<spring:message code="common.euro" /></td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
</c:if>