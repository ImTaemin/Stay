function likeClick(e){
	var id = $(e).attr("user_id");
	//alert(id);
	var tag = $(e);
	
	console.log(id);
	
	$.ajax({
		
		type: "get",
		dataType: "json",
		url: "updatelikes",
		data: {"id":id},
		success: function(data){
			//alert(data.chu);
			tag.next().text(data.likes);
		}
		
	});
}

$(".openBtn").click(function(){
			$("#singo-id").val($("#report_id").text());
		});
		
		 $("#bodyBtn").click(function(){
			var black_id = $("#report_id").text();
			var reason = $('#singo-reason').val();
			console.log(black_id);
			$.ajax({
				  type:"post",
				  dataType:"text",
				  url:"/profile/singo",
				  data:{"black_id":black_id,"reason":reason},
				  success:function(data) {
					  console.log("성공");
					  location.reload();							  
				  }
			   });
		 });
	
	
