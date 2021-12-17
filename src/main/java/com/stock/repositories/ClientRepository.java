package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
