/*
  const open = () => {
    document.querySelector(".modal").classList.remove("hidden");
  }

  const close = () => {
    document.querySelector(".modal").classList.add("hidden");
  }

  document.querySelector(".openBtn").addEventListener("click", open);
  document.querySelector(".closeBtn").addEventListener("click", close);
  document.querySelector(".bg").addEventListener("click", close);
*/

//수정 다이얼로그 띄우기
/*
   $(document).on("click","span.amod",function(){
	  
	   id=$(this).attr("id");
	   
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
/*
$(function(){

//댓글수정
	$(document).on("click","span.amod",function(){

		idx = $(this).attr("idx");
		//alert(idx);
		$.ajax({
			
			type:"get",
			dataType:"json",
			url:"adata",
			data:{"idx":idx},
			success: function(data){
				
				$("#modcontent").val(data.content);
				
				
			}
			
		});
		$("#myModal").modal();  //모달 띄우기
		
		/* $(".modbtn").click(function(){
			
			var content = $("#modcontent").val();
			$.ajax({
				
				type:"post",
				url:"aupdate",
				data:{"idx":idx,"content":content},
				success: function(data){

				},
				complete : function () {   // 정상이든 비정상인든 실행이 완료될 경우 실행될 함수
					alert("수정이 완료되었습니다");
					list();
				}

				
				
			});
			
		}); */
		
		/*
	});
	
	$(document).on("click",".modbtn",function(){
		
		var content = $("#modcontent").val();
		$.ajax({
			
			type:"post",
			url:"aupdate",
			data:{"idx":idx,"content":content},
			success: function(data){
				alert("신고가 접수되었습니다");
				list();
			}
			

			
			
		});
		
	});
	
	
}); */

/*
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
		 
			var num = $(this).attr("num");
			//alert(num);
			var id = $(this).attr("id");
			//alert(id);
			var tag = $(this);
			
			console.log(num);
			console.log(id);
			
			$.ajax({
				
				type: "get",
				dataType: "json",
				url: "updatelikes",
				data: {"num":num},
				data: {"id":id},
				success: function(data){
					//alert(data.chu);
					tag.next().text(data.likes);
					
						
					
				}
				
				
			});
			 
		 });
	
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
