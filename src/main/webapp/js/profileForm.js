
  const open = () => {
    document.querySelector(".modal").classList.remove("hidden");
  }

  const close = () => {
    document.querySelector(".modal").classList.add("hidden");
  }

  document.querySelector(".openBtn").addEventListener("click", open);
  document.querySelector(".closeBtn").addEventListener("click", close);
  document.querySelector(".bg").addEventListener("click", close);


//수정 다이얼로그 띄우기
/*
   $(document).on("click","span.amod",function(){
	  
	   idx=$(this).attr("idx");
	   
	   $.ajax({
		  type:"get",
		  dataType:"json",
		  url:"adata",
		  data:{"idx":idx},
		  success:function(data){
			  $("#ucontent").val(data.content);
		  }
	   });
	   
	   $("#myModal").modal();
	   
   });
*/


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

$(function(){
	
	 $("span.likes").click(function(){
		 
			var id = $(this).attr("id");
			//alert(id);
			var tag = $(this);
			
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
			 
		 });
	
});
/*
*/