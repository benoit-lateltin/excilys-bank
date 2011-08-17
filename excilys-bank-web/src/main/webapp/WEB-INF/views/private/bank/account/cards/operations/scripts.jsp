<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<c:choose>
	<c:when test="${not empty calendar.selectedMonth}">
		<c:set var="urlStart" value="${bk:ctx()}/private/bank/account/${account.number}/cards/${selectedCardNumber}/year/${calendar.selectedMonth.year().get()}/month/${calendar.selectedMonth.monthOfYear().get()}/page/" />
	</c:when>
	<c:otherwise>
		<c:set var="urlStart" value="${bk:ctx()}/private/bank/account/${account.number}/cards/${selectedCardNumber}/pending/page/" />
	</c:otherwise>
</c:choose>
<c:set var="urlEnd" value="/operations.json" />
<script>
function changePage(page) {
	paginate("${urlStart}" + page + "${urlEnd}");
}
changePage(0);
</script>
<script id="tbodyTemplate" type="text/x-jquery-tmpl">
{{each lines}}
	<tr><td class="date">\${\$value.date}</td><td>\${\$value.name}</td>
	{{if \$value.amount >= 0}}
		<td class="amount"><span class="figure green">\${\$value.amount}</span></td><td class="amount"></td>
	{{else}}
		<td class="amount"></td><td class="amount"><span class="figure red">\${\$value.amount}</span></td>
	{{/if}}
	</tr>
{{/each}}
{{each emptyLines}}
	<tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
{{/each}}
</script>
<script id="scrollerTemplate" type="text/x-jquery-tmpl">
	<div class="left">
		Showing
		<span id="startIndex">\${startIndex}</span>
		to
		<span id="endIndex">\${endIndex}</span>
		of
		<span id="totalElements">\${totalElements}</span>
		entries
	</div>
	<div class="right">
		<div id="back" class="left">
			{{if hasPreviousPage}}
				<div class="icon back enabled left" onclick="changePage(\${number - 1})"></div>
			{{else}}
				<div class="icon back disabled left"></div>
			{{/if}}
		</div>
		<div class="left">
			Page
			<span id="pageIndex">\${pageIndex}</span>
			of
			<span id="totalPages">\${totalPages}</span>
		</div>
		<div id="forward" class="left">
			{{if hasNextPage}}
				<div class="icon forward enabled left" onclick="changePage(\${number + 1})"></div>
			{{else}}
				<div class="icon forward disabled left"></div>
			{{/if}}
		</div>
		<div class="cb"></div>
	</div>
	<div class="cb"></div>
</script>
<c:if test="${cards.size() > 1}">
<c:set var="urlStart" value="${bk:ctx()}/private/bank/account/${account.number}/cards/" />
<c:choose>
	<c:when test="${not empty calendar.selectedMonth}">
		<c:set var="urlEnd" value="/year/${calendar.selectedMonth.year().get()}/month/${calendar.selectedMonth.monthOfYear().get()}/operations.json" />
	</c:when>
	<c:otherwise>
		<c:set var="urlEnd" value="/pending/operations.json" />
	</c:otherwise>
</c:choose>
<c:set var="urlEnd" value="" />
<script type="text/javascript">
$(function(){
	$('#selectedCard').bind('change', function () {
		var url = '${urlStart}' + this.value +'${urlEnd}';
		if (url !== '') {
			window.location = url;
		}
		return false;
	});
});
</script>
</c:if>
