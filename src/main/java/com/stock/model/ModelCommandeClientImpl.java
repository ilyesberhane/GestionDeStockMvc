package com.stock.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.stock.entities.Article;
import com.stock.entities.Client;
import com.stock.entities.CommandeClient;
import com.stock.entities.LigneCommandeClient;

@Component
public class ModelCommandeClientImpl implements ModelCommandeClient {
	
	private CommandeClient commande;
	private Map<Long, LigneCommandeClient> ligneCde = new HashMap<Long, LigneCommandeClient>();
	
	@Override
	public void init() {
		commande = null;
		ligneCde.clear();
		
	}

	@Override
	public void creerCommande() {
		commande = new CommandeClient();
		commande.setDateCommande(new Date());
		commande.setCode(generateCodeCommande());
	}
	
	@Override
	public void modifierCommande(Client client) {
		if (client == null) {
			return;
		}
		if (commande != null) {
			commande.setClient(client);
		}
	}

	@Override
	public LigneCommandeClient ajouterLigneCommande(Article article) { 
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = ligneCde.get(article.getId());
		if (lc != null) {
			BigDecimal qte = lc.getQuantite().add(BigDecimal.ONE);
			lc.setQuantite(qte);
			ligneCde.put(article.getId(), lc);
			return lc;
		} else {
			LigneCommandeClient ligne = new LigneCommandeClient();
			ligne.setCommandeClient(commande);
			ligne.setQuantite(BigDecimal.ONE);
			ligne.setPrixUnitaire(article.getPrixUnitaireTTC());
			ligne.setArticle(article);
			ligneCde.put(article.getId(), ligne);
			return ligne;
		}
	}

	@Override
	public LigneCommandeClient supprimerLigneCommande(Article article) {
		if (article == null) {
			return null;
		}
		return ligneCde.remove(article.getId());
	}

	@Override
	public LigneCommandeClient modifierQuantite(Article article, double qte) {
		if (article == null) {
			return null;
		}
		LigneCommandeClient lc = ligneCde.get(article.getId());
		if (lc == null) {
			return null;
		}
		lc.setQuantite(BigDecimal.valueOf(qte));
		return lc;
	}
	
	@Override
	public Collection<LigneCommandeClient> getLignesCommandeClient(CommandeClient commande) {
		for (LigneCommandeClient ligneCdeClt : ligneCde.values()) {
			ligneCdeClt.setCommandeClient(commande);
		}
		return ligneCde.values();
	}

	@Override
	public String generateCodeCommande() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase() ;
	}

	@Override
	public CommandeClient getCommande() {
		return commande;
	}

	public void setCommande(CommandeClient commande) {
		this.commande = commande;
	}

	@Override
	public Map<Long, LigneCommandeClient> getLigneCde() {
		return ligneCde;
	}

	public void setLigneCde(Map<Long, LigneCommandeClient> ligneCde) {
		this.ligneCde = ligneCde;
	}
}
