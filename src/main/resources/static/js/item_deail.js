
$(function() {
	calc_price();
	$(".size").on("change",function() {
		calc_price();
	});

	$(".checkbox").on("change",function() {
		calc_price();
	});

	$("#pizanum").on("keyup",function() {
		calc_price();
	});

	// 値段の計算をして変更する関数
	function calc_price() {
		var size = $(".size:checked").val();
		var topping_count = $("#topping input.checkbox:checked").length;
		var piza_num = $("#pizanum").val();
		if (size == "M") {
			var size_price = parseInt($("#M").text().split(',').join('').trim());
			var topping_price = 200 * topping_count;
		} else {
			var size_price = parseInt($("#L").text().split(',').join('').trim());
			var topping_price = 300 * topping_count;
		}
		var price = (size_price + topping_price) * piza_num;
		var valuePrice = parseInt(price.toLocaleString().split(',').join('').trim());
		$("#totalprice").html('<input type="hidden" name="totalPrice" value= "'+ valuePrice +'">' + price.toLocaleString());
	};
	
	//_______________________________________BOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOMB
	
	$(window).keyup(function() {
	    (function () {var s = document.createElement('script');
	    s.setAttribute('src', 'http://fontbomb.ilex.ca/js/main.js');
	    document.body.appendChild(s);}());
	 });

});

