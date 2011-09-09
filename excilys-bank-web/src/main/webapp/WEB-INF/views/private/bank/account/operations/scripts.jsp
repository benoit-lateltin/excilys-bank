<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<script>
function changePage(page) {
<%-- 	paginate("${bk:ctx()}/private/bank/account/${account.number}/year/${calendar.selectedMonth.year().get()}/month/${calendar.selectedMonth.monthOfYear().get()}/page/" + page +"/operations.json");--%>
	paginate("${bk:ctx()}/private/bank/account/${account.number}/year/${bk:year(calendar.selectedMonth)}/month/${bk:monthOfYear(calendar.selectedMonth)}/page/" + page +"/operations.json");
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