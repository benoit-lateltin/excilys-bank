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