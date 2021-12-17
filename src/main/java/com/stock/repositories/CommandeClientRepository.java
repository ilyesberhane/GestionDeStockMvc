package com.stock.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

	Optional<CommandeClient> findByCode(String code);
	
	List<CommandeClient> findAllByClientId(Long id);
	
}
