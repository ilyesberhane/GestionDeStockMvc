package com.stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Vente;

public interface VentesRepository extends JpaRepository<Vente, Long> {

	Optional<Vente> findByCode(String code);
	
}
