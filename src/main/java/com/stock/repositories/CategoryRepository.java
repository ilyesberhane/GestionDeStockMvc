package com.stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByCode(String categoryCode);
	
//	public static Category findByid(Long id)
//	{
//	    Category result = (Category) getSession().find(Category.class, id);
//	    Hibernate.initialize(result.getArticles());
//	    return result;
//	}
	
}
