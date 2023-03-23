function callServer() {
	// $("#myId") is equivalent to vanilla JS of 
	// saying document.getElementById('myId')
	const plId = $("#id").val();
	console.log(plId);
	$.ajax({
		method : "GET",
		url : "/submitLeadMyBatisPLById",
		data : {plID : plId}
	}).done(function(msg) {
		console.log(msg);
		const jsonObj = JSON.parse(msg);
		Object.entries(jsonObj[0]).forEach(([key, val]) => {
			if(key != 'website') {
				$("#" + key).val(val);
			}
		});
		$("#websiteURL").html(jsonObj[0].website);
	});
}