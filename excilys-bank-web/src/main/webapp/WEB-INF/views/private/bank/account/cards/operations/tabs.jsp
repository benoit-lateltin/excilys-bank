<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<ul class="tabs">
	<c:forEach items="${calendar.months}" var="month">
		<li class="${bk:if(month eq calendar.selectedMonth, 'active', '')}">
			<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${selectedCardNumber}/year/${bk:year(month)}/month/${bk:monthOfYear(month)}/operations.html">
				${bk:monthOfYearAsText(month)}
			</a>
<%-- 			<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${selectedCardNumber}/year/${month.year().get()}/month/${month.monthOfYear().get()}/operations.html"> --%>
<%-- 				${month.monthOfYear().getAsText()} --%>
<!-- 			</a> -->
		</li>
	</c:forEach>
	<li class="right ${bk:if(empty calendar.selectedMonth, 'active', '')}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${selectedCardNumber}/pending/operations.html">Pending</a>
	</li>
	<c:choose>
<%-- 		<c:when test="${account.cards.size() == 1}"> --%>
			<c:when test="${bk:size(account.cards) == 1}">
			<li>
				<c:out value="${account.cards[0].type.id}"/> - <c:out value="${account.cards[0].number}"/>
			</li>
		</c:when>
		<c:otherwise>
			<li>
				<div>
					<div id="cardList">
						<form>
							<select id="selectedCard">
								<c:set var="selected" value="${empty selectedCardNumber?'selected':''}" />
								<option value="all" <c:out value="${selected}" />>
									All
								</option>
								<c:forEach items="${account.cards}" var="card">
									<c:set var="selected" value="${card.number eq selectedCardNumber?'selected':''}" />
									<option value="${card.number}" <c:out value="${selected}" />>
										${card.type.id} - ${card.number}
									</option>
								</c:forEach>
							</select>
						</form>
					</div>
				</div>
			</li>
		</c:otherwise>
	</c:choose>
</ul>