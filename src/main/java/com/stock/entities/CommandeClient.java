package com.stock.entities;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CommandeClient implements Serializable{

	private static final long serialVersionUID = -2390674843787025224L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommande;
	
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	
	@OneToMany(mappedBy = "commandeClient")
	@JsonIgnore
	@JsonManagedReference
	private List<LigneCommandeClient> ligneCommandeClients;
	
	
	@Transient
	private BigDecimal totalCommande;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	public List<LigneCommandeClient> getLigneCommandeClients() {
		return ligneCommandeClients;
	}

	public void setLigneCommandeClients(List<LigneCommandeClient> ligneCommandeClients) {
		this.ligneCommandeClients = ligneCommandeClients;
	}
	
	public BigDecimal getTotalCommande() {
		totalCommande = BigDecimal.ZERO;
		if (!ligneCommandeClients.isEmpty()) {
			for (LigneCommandeClient ligneCommandeClient : ligneCommandeClients) {
				if (ligneCommandeClient.getQuantite() != null && ligneCommandeClient.getPrixUnitaire() != null) {
					BigDecimal totalLigne = ligneCommandeClient.getQuantite().multiply(ligneCommandeClient.getPrixUnitaire());
					totalCommande = totalCommande.add(totalLigne);
				}
			}
		}
		return totalCommande;
	}
	
	@Transient
	public String getLigneCommandeJson() {
		if (!ligneCommandeClients.isEmpty()) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(ligneCommandeClients);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
