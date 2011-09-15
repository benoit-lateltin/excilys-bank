<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<div class="block">
	<div class="body">
		<div class="left">
			<div class="title">
				<span class="icon bank left"></span>
				<span class="iconLabel left">
					${account.type.id}
					<br />
					${account.number}
				</span>
				<div class="cb"></div>
			</div>
		</div>
		<div class="right">
			<table class="accountDetails">
				<tbody>
					<tr>
						<td>
							<spring:message code="accounts.details.balance" />
							<joda:format value="${account.balanceDate}" pattern="MM/dd/yyyy"/>
						</td>
						<td class="amount">${bk:amount(account.balance)} <spring:message code="common.euro" /></td>
					</tr>
					<tr>
						<td><spring:message code="accounts.details.pending" /></td>
						<td class="amount">${bk:amount(account.totalPending)} <spring:message code="common.euro" /></td>
					</tr>
					<tr>
						<td><spring:message code="accounts.details.estimatedBalance" /></td>
						<td class="amount">${bk:amount(account.estimatedBalance)} <spring:message code="common.euro" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="cb"></div>
	</div>
</div>







