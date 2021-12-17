package com.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {

	List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Long idCommande);

	List<LigneCommandeFournisseur> findAllByArticleId(Long idCommande);
	
}
