function supprimerArticle(url) {
	alert("dsdssd");
	$.getJSON(
		url, 
		{ajax:true},
		function(data) {
			$("#errorBlock").show();
			$("#errorMsg").val(data);
		}
	);
}