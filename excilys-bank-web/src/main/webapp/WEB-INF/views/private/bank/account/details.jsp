<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
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
						<td>Solde au ${account.balanceDate.toString("dd/MM/yyyy")}</td>
						<td class="amount">${bk:amount(account.balance)} &euro;</td>
					</tr>
					<tr>
						<td>Total Pending</td>
						<td class="amount">${bk:amount(account.getTotalPending())} &euro;</td>
					</tr>
					<tr>
						<td>Estimated balance</td>
						<td class="amount">${bk:amount(account.getEstimatedBalance())} &euro;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="cb"></div>
	</div>
</div>







