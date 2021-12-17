package com.stock.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.stock.entities.Fournisseur;
import com.stock.repositories.FournisseurRepository;
import com.stock.services.FlickrImpl;

@Controller
@RequestMapping(value = "/fournisseur")
public class FournisseurController {
	
	@Autowired
	private FournisseurRepository fournisseurRepository;
	
	@Autowired
	private FlickrImpl flickrImpl;
	
	@RequestMapping(value = "/")
	public String fournisseur(Model model) {
		List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
		if (fournisseurs == null) {
			fournisseurs = new ArrayList<Fournisseur>();
		}
		model.addAttribute("fournisseurs", fournisseurs);
		
		return "fournisseur/fournisseur";
	}
	
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterFournisseur(Model model) {
		Fournisseur fournisseur = new Fournisseur();
		model.addAttribute("fournisseur", fournisseur);
		
		return "fournisseur/ajouterFournisseur";
	}
	
	@RequestMapping(value = "/enregistrer")
	public String enregistrerFournisseur(Model model, Fournisseur fournisseur, MultipartFile file) {
		String photoUrl = null;
		if (fournisseur != null) {
			if (file != null && !file.isEmpty()) {
				InputStream stream = null;
				try {
					stream = file.getInputStream();
					photoUrl = flickrImpl.savePhoto(stream, fournisseur.getNom());
					fournisseur.setPhoto(photoUrl);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			fournisseurRepository.save(fournisseur);
			
		}
		return "redirect:/fournisseur/";
	}
	
	@RequestMapping(value = "/modifier/{id}")
	public String modifierFournisseur(Model model, @PathVariable Long id) {
		if (id != null) {
			Fournisseur fournisseur = fournisseurRepository.findById(id).get();
			if (fournisseur != null) {
				model.addAttribute("fournisseur", fournisseur);
			}
		}
		return "fournisseur/ajouterFournisseur";
	}
	
	@RequestMapping(value = "/supprimer/{id}")
	public String supprimerFournisseur(Model model, @PathVariable Long id) {
		if (id != null) {
			Fournisseur Fournisseur = fournisseurRepository.findById(id).get();
			if (Fournisseur != null) {
				fournisseurRepository.deleteById(id);
			}
		}
		return "redirect:/fournisseur/";
	}
}
