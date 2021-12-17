package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
