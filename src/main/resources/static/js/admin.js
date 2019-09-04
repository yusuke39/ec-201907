
$(function(){
	
		$("#itemButton").on("click",function(){
			for(var i = 1; i <= $("#itemCount").val() ; i++){
				$("#html").append('<br>'
				+'<br>'
				+'おもちゃ名:<br>'
				+'<input type="text" name="name" ><br>'
				+'おもちゃの説明:<br>'
				+'<textarea rows="10" cols="30" name="description"></textarea><br>'
				+'Mサイズの値段:<br>'
				+'<input type="text" name="priceM" ><br>'
				+'Lサイズの値段:<br>'
				+'<input type="text" name="priceL" ><br>'
				+'画像を選択:<br>'
				+'<input type="file" class="dropify" name="image_path"><br>'
				+'<br>'); 
			}
		});
		
		$("#itemButton").on("click",function(){
			$("#itemButton").prop('disabled', true);
		});
		
//		$(document).ready(function(){
//			$('.dropify').dropify();
//		});
	});