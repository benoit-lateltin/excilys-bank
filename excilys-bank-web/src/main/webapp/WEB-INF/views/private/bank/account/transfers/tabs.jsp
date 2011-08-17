<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<ul class="tabs">
	<li class="${bk:if(page.hasAncestor('TRANSFER_OPERATIONS'), 'active', '')}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
			Operations
		</a>
	</li>
	<li class="${bk:if(page.hasAncestor('TRANSFER_PERFORM'), 'active', '')}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/perform.html">
			Perform
		</a>
	</li>
</ul>