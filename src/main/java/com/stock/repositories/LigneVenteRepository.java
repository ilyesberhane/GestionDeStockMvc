package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {

//	List<LigneVente> findAllByArticleId(Long idArticle);
//	
//	List<LigneVente> findAllByVenteId(Long id);
	
}
