package com.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.CommandeFournisseur;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {

	
	List<CommandeFournisseur> findAllByFournisseurId(Long id);
	
}
