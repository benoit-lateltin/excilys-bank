function stripe() {
	$(".striped tr").mouseover(function() {
		$(this).addClass("over");
	}).mouseout(function() {
		$(this).removeClass("over");
	});
	$(".striped tr:nth-child(even)").addClass("alt");
}

function message(str) {
	$.notify_osd.create({
		'text' : str,
		'sticky' : true,
		'dismissable' : true,
		'click_through' : false,
		'opacity_min' : 1,
		'opacity_max' : 1,
	});
}
