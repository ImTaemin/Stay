function changeCheckIn() {
	var startDate = $('#re-check-in').val();

	var start = startDate.split("-");

	$('label[id=start1]').html(start[0]);
	$('label[id=start2]').html(start[1]);
	$('label[id=start3]').html(start[2]);
	
	$('input[name=startDate]').attr('value', startDate);
}

function changeCheckOut() {
	var endDate = $('#re-check-out').val();

	var end = endDate.split("-");

	$('label[id=end1]').html(end[0]);
	$('label[id=end2]').html(end[1]);
	$('label[id=end3]').html(end[2]);
	
	$('input[name=endDate]').attr('value', endDate);
}