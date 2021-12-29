function btnClick(e) {
	var s = "<form action='hostreservation' method='post'>";
	s += "<input type='hidden' name='reserNo' value='" + $(e).attr("list-no") + "'>";
	s += "</form>";
	
	$(e).parent().append(s);
	$(e).siblings('form:eq(1)').detach();
	$(e).siblings('form:eq(0)').submit();
}