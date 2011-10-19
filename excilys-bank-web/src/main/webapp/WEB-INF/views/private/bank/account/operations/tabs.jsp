<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<ul class="tabs">
	<c:forEach items="${calendar.months}" var="month">
		<li class="${bk:if(month eq calendar.selectedMonth, 'active', '')}">
<%-- 			<a href="${bk:ctx()}/private/bank/account/${account.number}/year/${month.year().get()}/month/${month.monthOfYear().get()}/operations.html"> --%>
<%-- ${month.monthOfYear().getAsText()} --%>
			<a href="${bk:ctx()}/private/bank/account/${account.number}/year/${bk:year(month)}/month/${bk:monthOfYear(month)}/operations.html">
				${bk:monthOfYearAsText(month)}
			</a>
		</li>
	</c:forEach>
</ul>