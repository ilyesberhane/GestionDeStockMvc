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

import com.stock.entities.Client;
import com.stock.repositories.ClientRepository;
import com.stock.services.FlickrImpl;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	

	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private FlickrImpl flickrImpl;
	
	
	@Autowired
	public ClientController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	
	@RequestMapping(value = "/")
	public String client(Model model) {
//		List<Client> clients = clientService.selectAll();
		List<Client> clients = clientRepository.findAll();
		if (clients == null) {
			clients = new ArrayList<Client>();
		}
		model.addAttribute("clients", clients);
		return "client/client";
	}
	
	
	
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "client/ajouterClient";
	}
	
	@RequestMapping(value = "/enregistrer", method = RequestMethod.POST)
	public String enregistrerClient(Model model, Client client, MultipartFile file) {
		
		String photoUrl = null;
		
		if (client != null) {
			if (file != null && !file.isEmpty()) {
				InputStream stream = null;
				try {
					stream = file.getInputStream();
					photoUrl = flickrImpl.savePhoto(stream, client.getNom());
					client.setPhoto(photoUrl);
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
			
			clientRepository.save(client);
		
		}
		return "redirect:/client/";
	}
	
	@RequestMapping(value = "/modifier/{id}")
	public String modifierClient(Model model, @PathVariable Long id) {
		if (id != null) {
//			Client client = clientService.getById(id);
			Client client = clientRepository.findById(id).get();
			if (client != null) {
				model.addAttribute("client", client);
			}
		}
		return "client/ajouterClient";
	}
	
	@RequestMapping(value = "/supprimer/{id}")
	public String supprimerClient(Model model, @PathVariable Long id) {
		if (id != null) {
//			Client client = clientService.getByid(id);
			Client client = clientRepository.getById(id);
			if (client != null) {
				//TODO Verification avant suppression
//				clientService.remove(id);
				clientRepository.deleteById(id);
			}
		}
		return "redirect:/client/";
	}
}
