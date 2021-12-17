package com.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.LigneCommandeClient;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {

	List<LigneCommandeClient> findAllByCommandeClientId(Long id);
	
	List<LigneCommandeClient> findAllByArticleId(Long idCommande);
	
}
