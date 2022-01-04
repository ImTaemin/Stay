$(document).ready(function() {
	// 날짜 계산
	var startDate = $("input[name=startDate]").attr("value").split("-");
	var endDate = $("input[name=endDate]").attr("value").split("-");

	var start = new Date(startDate[0], startDate[1], startDate[2]);
	var end = new Date(endDate[0], endDate[1], endDate[2]);

	var btMs = end.getTime() - start.getTime();
	var betweenDay = btMs / (1000 * 60 * 60 * 24);
	
	$("span[id=betweenDay]").html(betweenDay + "박");
	
	// 숙박 가격 계산
	var roomPrice = $("input[name=roomPrice]").attr("value");
	var calPrice = roomPrice * betweenDay;
	var taxPrice = calPrice * 0.2;
	var allPrice = parseInt(calPrice) + parseInt(taxPrice);
	
	$("span[id=calPrice]").html("￦" + calPrice.toLocaleString());
	$("span[id=taxPrice]").html("￦" + taxPrice.toLocaleString());
	$("span[id=allPrice]").html("￦" + allPrice.toLocaleString());
});

// 이미지 다운로드
function PrintDiv(div){
	var reserNo = $("input[name=reserNo]").attr("value");
	
	div = div[0]
	html2canvas(div).then(function(canvas){
		var myImage = canvas.toDataURL();
		downloadURI(myImage, reserNo + ".png")
	});
}

function downloadURI(uri, name){
	var link = document.createElement("a")
	link.download = name;
	link.href = uri;
	document.body.appendChild(link);
	link.click();
}