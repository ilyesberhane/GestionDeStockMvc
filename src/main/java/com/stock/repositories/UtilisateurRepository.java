package com.stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stock.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	// JPQL query
	  @Query(value = "select u from Utilisateur u where u.mail = :mail")
	  Optional<Utilisateur> findByMail(/*@Param("email")*/ String mail);
	  
	  
	
}
