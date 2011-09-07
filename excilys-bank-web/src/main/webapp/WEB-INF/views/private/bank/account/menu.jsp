<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="submenu ${bk:if(empty account.cards, 'nocards', '')}">
	<div class="block left">
		<div class="body">
			<div class="title ${bk:if(page.hasAncestor('ACCOUNT_OPERATIONS'), 'active', '')}">
				<a href="${bk:ctx()}/private/bank/account/${account.number}/operations.html">
					<span class="icon operation left"></span>
					<span class="iconLabel left">Operations</span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<c:if test="${not empty account.cards}">
		<div class="block left">
			<div class="body">
				<div class="title ${bk:if(page.hasAncestor('CARDS'), 'active', '')}">
					<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/all/operations.html">
						<span class="icon card left"></span>
						<span class="iconLabel left">Cards</span>
					</a>
					<div class="cb"></div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="block left">
		<div class="body">
			<div class="title ${bk:if(page.hasAncestor('TRANSFERS'), 'active', '')}">
				<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
					<span class="icon transfer left"></span>
					<span class="iconLabel left">Transfers</span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<div class="cb"></div>
</div>