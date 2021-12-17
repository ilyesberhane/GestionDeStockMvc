$(document).ready(function() {
	$("#codeArticle_search").on("keypress", function(e) {
		if(e.which == '13') {
			var codeArticle = $("#codeArticle_search").val();
			if(verifierClient() && codeArticle) {
				searchArticle(codeArticle);
			}
		}
	});
	
	$("#listClients").on("change", function(e) {
		if(verifierClient()) {
			$("#clientNotSelectedMsgBlock").hide("slow", function() {$("#clientNotSelectedMsgBlock").hide()});
			creerCommande($("#listClients option:selected").val());
		}
	});

	
	$("#notFoundMsgBlock").hide();
	$("#clientNotSelectedMsgBlock").hide();
	$("#unexpectedErrorMsgBlock").hide();
	
});

function verifierClient() {
	var idClient = $("#listClients option:selected").val();
	if(idClient) {
		if (idClient === "-1") {
			$("#clientNotSelectedMsgBlock").show("slow", function() {$("#clientNotSelectedMsgBlock").show()});
			return false;
		}
		return true;
	}
}

function creerCommande(idClient) {
	if(idClient) {
		$.getJSON("creerCommande", {
			idClient: idClient,
			ajax: true
		},
		function(data) {
			console.log("client a ete mis a jour");
		});
	}
}

function updateDetailCommande(idCommande) {
	var json = $.parseJSON($("#json" + idCommande).text());
	var detailHtml = "";
	if(json) {
		for(var index = 0; index < json.length; index++) {
			detailHtml += 
						"<tr>"+
							"<td>" + json[index].article.codeArticle + "</td>"+
							"<td>" + json[index].quantite + "</td>"+
							"<td>" + json[index].quantite + "</td>"+
							"<td>0</td>"+
						"</tr>";
		}
		$("#detailCommande").html(detailHtml);
	}	
}

function searchArticle(codeArticle) {
	if(codeArticle) {
		var detailHtml = "";
		$.getJSON("ajouterLigne", {
			codeArticle: codeArticle,
			ajax: true
		}, 
		function(data) {
			if(data) {
				var total = data.quantite * data.prixUnitaire;
				if($("#qte" + data.article.idArticle).length > 0 && $("#total" + data.article.idArticle).length > 0) {
					$("#qte" + data.article.idArticle).text(data.quantite);
					$("#total" + data.article.idArticle).text(total);
				} else {
					detailHtml += 
						"<tr id='ligne" + data.article.idArticle + "'>"+
							"<td>" + data.article.codeArticle + "</td>"+
							"<td id='qte" + data.article.idArticle + "'>" + data.quantite + "</td>"+
							"<td>" + data.prixUnitaire + "</td>"+
							"<td id='total" + data.article.idArticle + "'>" + total + "</td>"+
							"<td ><button class='btn btn-link' onclick='supprimerLigneCommande(" + data.article.idArticle + ")'><i class='fa fa-trash-o'></i></button></td>"+
						"</tr>";
					$("#detailNouvelleCommande").append(detailHtml);
				}
				$("#notFoundMsgBlock").hide("slow", function() {$("#notFoundMsgBlock").hide()});
				$("#codeArticle_search").val("");
			}
		}).fail(function() {
			$("#notFoundMsgBlock").show("slow", function() {$("#notFoundMsgBlock").show()});
		});
	}	
}


function supprimerLigneCommande(idArticle) {
	if($("#ligne" + idArticle).length > 0) {
		$.getJSON("supprimerLigne", {
			idArticle: idArticle,
			ajax: true
		},
		function(data){
			if(data) {
				$("#ligne" + idArticle).hide("slow", function() {$("#ligne" + idArticle).remove()});
			}
		});
	}
}