package com.stock.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.entities.Article;
import com.stock.entities.Category;
import com.stock.repositories.ArticleRepository;
import com.stock.repositories.CategoryRepository;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value = "/")
	public String category(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Category> categories = categoryRepository.findAll();
		if (categories == null) {
			categories = new ArrayList<Category>();
		}
		model.addAttribute("categories", categories);
		
		return "category/category";
	}
	
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterClient(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/ajouterCategory";
	}
	
	@RequestMapping(value = "/enregistrer")
	public String enregistrerClient(Model model, Category category) {
		if (category != null) {
			categoryRepository.save(category);
		}
		return "redirect:/category/";
	}
	
	@RequestMapping(value = "/modifier/{id}")
	public String modifierClient(Model model, @PathVariable Long id) {
		if (id != null) {
			Category category = categoryRepository.findById(id).get();
			if (category != null) {
				model.addAttribute("category", category);
			}
		}
		return "category/ajouterCategory";
	}
	
	@RequestMapping(value = "/supprimer/{id}")
	@ResponseBody
	public String supprimerClient(Model model, @PathVariable Long id) {
		if (id != null) {
			Category category = categoryRepository.findById(id).get();
			if (category != null) {
				List<Article> articleCat = articleRepository.findAllByCategoryId(id);
				if (articleCat.isEmpty() ) {
					categoryRepository.deleteById(id);
				} else {
					return "Impossible de supprimer cette catégprie, car elle est affecté à des articles";
				}
			}
		}
		return "redirect:/category/";
	}

}
