
$(function () {
	$('#inputZipcode').jpostal({
		click : '#btn',
		postcode : [
			'#inputZipcode'
		],
		address : {
			'#inputAddress' : '%3%4%5'
		}
	});
});

