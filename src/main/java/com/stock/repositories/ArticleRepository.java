package com.stock.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

//	@Query("select a from article where codearticle = :code and designation = :designation") //Requete JPQL
//	List<Article> findByCustomQuery(@Param("codearticle")String code, @Param("designation") String designation);
//	
//	
//	@Query(value = "select * from article where code = :code", nativeQuery = true) //Requete SQL Native
//	List<Article> findByCustomNativeQuery(@Param("code")String code);
//	
//	
//	List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArticle, String designation);
//	//			  findByColumn1AndColumn2AndColumn3....				IgnoreCase --> ignorer la casse
	
	
	Optional<Article> findByCode(String codeArticle);
	
	List<Article> findAllByCategoryId(Long idCategory);
}
