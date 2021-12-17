package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

}
