package com.stock.model;

import java.util.Collection;
import java.util.Map;

import com.stock.entities.Article;
import com.stock.entities.Client;
import com.stock.entities.CommandeClient;
import com.stock.entities.LigneCommandeClient;

public interface ModelCommandeClient {

	void creerCommande();
	
	void modifierCommande(Client client);
	
	LigneCommandeClient ajouterLigneCommande(Article article);
	
	LigneCommandeClient supprimerLigneCommande(Article article);
	
	LigneCommandeClient modifierQuantite(Article article, double qte);
	
	String generateCodeCommande();
	
	CommandeClient getCommande();
	
	Map<Long, LigneCommandeClient> getLigneCde();
	
	Collection<LigneCommandeClient> getLignesCommandeClient(CommandeClient commande);
	
	void init();
}
