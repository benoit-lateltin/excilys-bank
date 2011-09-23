<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="block transfers">
	<div id="operationsContainer" class="body">
		<table id="operations" class="operations striped">
			<thead>
				<tr>
					<th class="dateHeader"><spring:message code="operations.date" /></th>
					<th><spring:message code="operations.name" /></th>
					<th class="amountHeader"><spring:message code="operations.credit" /></th>
					<th class="amountHeader"><spring:message code="operations.debit" /></th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<bk:ite count="20">
					<tr>
						<td>&nbsp;</td><td></td><td></td><td></td><td></td>
					</tr>
				</bk:ite>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="scroller"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>