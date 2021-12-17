package com.stock.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.entities.Article;
import com.stock.entities.Client;
import com.stock.entities.CommandeClient;
import com.stock.entities.LigneCommandeClient;
import com.stock.model.ModelCommandeClient;
import com.stock.repositories.ArticleRepository;
import com.stock.repositories.ClientRepository;
import com.stock.repositories.CommandeClientRepository;
import com.stock.repositories.LigneCommandeClientRepository;

@Controller
@RequestMapping(value = "/commandeclient")
public class CommandeClientController {
	
	@Autowired
	private CommandeClientRepository commandeClientRepository;
	
	@Autowired
	private LigneCommandeClientRepository ligneCommandeClientRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ModelCommandeClient modelCommande;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		List<CommandeClient> commandesClient = commandeClientRepository.findAll();
		if (commandesClient.isEmpty()) {
			commandesClient = new ArrayList<CommandeClient>();
		} else {
			System.out.println("\n\n\n");
			System.out.println(commandesClient);
			System.out.println("\n\n\n");
			for (CommandeClient commandeClient : commandesClient) {
				List<LigneCommandeClient> ligneCdeClt = ligneCommandeClientRepository.findAllByCommandeClientId(commandeClient.getId());
				System.out.println("\n\n\n");
				System.out.println(ligneCdeClt);
				System.out.println("\n\n\n");
				commandeClient.setLigneCommandeClients(ligneCdeClt);
			}
		}
		
		model.addAttribute("commandesClient", commandesClient);
		
		return "commandeclient/commandeclient";
	}
	
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model) {
		List<Client> clients = clientRepository.findAll();
		if (clients.isEmpty()) {
			clients = new ArrayList<Client>();
		}
		modelCommande.creerCommande();
		model.addAttribute("code", modelCommande.getCommande().getCode());
		model.addAttribute("dateCde", modelCommande.getCommande().getDateCommande());
		model.addAttribute("clients", clients);
		
		return "commandeclient/nouvellecommande";
	}
	
	@RequestMapping(value = "/creerCommande")
	@ResponseBody
	public String creerCommande(final Long idClient) {
		if (idClient == null) {
			return null;
		}
		Client client = clientRepository.findById(idClient).get();
		if (client == null) {
			return null;
		}
		
		return "OK";
	}
	
	@RequestMapping(value = "/ajouterLigne")
	@ResponseBody
	public LigneCommandeClient getArticleByCode(final String codeArticle) {
		if (codeArticle == null) {
			return null;
		}
		Article article = articleRepository.findByCode(codeArticle).get();
		if (article == null) {
			return null;
		}
		return modelCommande.ajouterLigneCommande(article); 
	}
	
	@RequestMapping(value = "/supprimerLigne")
	@ResponseBody
	public LigneCommandeClient supprimerLigneCommande(final Long idArticle) {
		if (idArticle == null) {
			return null;
		}
		Article article = articleRepository.getById(idArticle);
		if (article == null) {
			return null;
		}
		return modelCommande.supprimerLigneCommande(article);
	}
	
//	@RequestMapping(value = "/enrigstrerCommande", produces = "application/json")
//	@ResponseBody
//	public StringResponse enrgistrerCommande(HttpServletRequest request) {
//		CommandeClient nouvelleCommande = null;
//		if (modelCommande.getCommande() != null) {
//			nouvelleCommande = commandeClientRepository.save(modelCommande.getCommande());
//		}
//		if (nouvelleCommande != null) {
//			Collection<LigneCommandeClient> ligneCommandes = modelCommande.getLignesCommandeClient(nouvelleCommande);
//			if (ligneCommandes != null && !ligneCommandes.isEmpty()) {
//				for (LigneCommandeClient ligneCommandeClient : ligneCommandes) {
//					ligneCommandeClientRepository.save(ligneCommandeClient);
//				}
//				modelCommande.init();
//			}
//		}
//		
//		return new StringResponse(request.getContextPath() + "/commandeclient");
//	}
	
	
	@RequestMapping(value = "/enrigstrerCommande")
	public String enrgistrerCommande(HttpServletRequest request) {
		CommandeClient nouvelleCommande = null;
		if (modelCommande.getCommande() != null) {
			nouvelleCommande = commandeClientRepository.save(modelCommande.getCommande());
		}
		if (nouvelleCommande != null) {
			Collection<LigneCommandeClient> ligneCommandes = modelCommande.getLignesCommandeClient(nouvelleCommande);
			if (ligneCommandes != null && !ligneCommandes.isEmpty()) {
				for (LigneCommandeClient ligneCommandeClient : ligneCommandes) {
					ligneCommandeClientRepository.save(ligneCommandeClient);
				}
				modelCommande.init();
			}
		}
		
		return "redirect:/commandeclient/";
	}

}
