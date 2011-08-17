<script type='text/javascript'>
$(function(){
	$('#debitedAccountNumber').bind('change', function () {
		var debitedAccountNumber = $(this).val();
		var options = '';
		$(this).children("option").each(function(i, option){
			var optionValue = $(option).val();
			var optionText = $(option).text();
			if (optionValue != debitedAccountNumber)
				options += "<option value='" + optionValue + "'>" + optionText + "</option>";
		});
		return $('#creditedAccountNumber').html(options);
	});
});
</script>
