<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="block">
	<div id="operationsContainer" class="body">
		<table id="operations" class="operations striped">
			<thead>
				<tr>
					<th class="dateHeader">Date</th>
					<th>Libellé</th>
					<th class="amountHeader">Crédit</th>
					<th class="amountHeader">Débit</th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th class="amount">${bk:amount(creditSum)}</th>
					<th class="amount">${bk:amount(debitSum)}</th>
				</tr>
			</thead>
			<tbody>
				<bk:ite count="20">
					<tr>
						<td>&nbsp;</td><td></td><td></td><td></td>
					</tr>
				</bk:ite>
			</tbody>
			<tfoot>
				<c:forEach items="${cardSums}" var="cardSum">
					<tr>
						<td></td>
						<td>
							<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${cardSum.card.number}/operations.html">
								<span class="icon card left"></span>
								<span class="iconLabel">${cardSum.card.type.id} ${cardSum.card.number}</span>
							</a>
						</td>
						<c:choose>
							<c:when test="${cardSum.amount > 0}">
								<td class="amount">${bk:amount(cardSum.amount)}</td>
								<td class="amount"></td>
							</c:when>
							<c:otherwise>
								<td class="amount"></td>
								<td class="amount">${bk:amount(cardSum.amount)}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">
						<div id="scroller"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>