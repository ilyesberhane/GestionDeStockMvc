package com.stock.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.stock.entities.Article;
import com.stock.entities.Category;
import com.stock.export.FileExporter;
import com.stock.repositories.ArticleRepository;
import com.stock.repositories.CategoryRepository;
import com.stock.services.FlickrImpl;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Autowired
	private FlickrImpl flickrImpl;
	
	
	
	
	
	@Autowired
	@Qualifier("articleExporter")
	private FileExporter exporter;
	
	
	
	@RequestMapping(value = "/")
	public String article(Model model) {
		List<Article> articles = articleRepository.findAll();
		if (articles == null) {
			articles = new ArrayList<Article>();
		}
		model.addAttribute("articles", articles);
		return "article/article";
	}
	
	

	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterArticle(Model model) {
		Article article = new Article();
		List<Category> categories = categoryRepository.findAll();
		if (categories == null) {
			categories = new ArrayList<Category>();
		}
		model.addAttribute("article", article);
		model.addAttribute("categories", categories);
		
		return "article/ajouterArticle";
	}
	
	@RequestMapping(value = "/enregistrer")
	public String enregistrerArticle(Model model, Article article, MultipartFile file) {
		String photoUrl = null;
		if (article != null) {
			if (file != null && !file.isEmpty()) {
				InputStream stream = null;
				try {
					stream = file.getInputStream();
					photoUrl = flickrImpl.savePhoto(stream, article.getCodeArticle());
					article.setPhoto(photoUrl);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			articleRepository.save(article);
			
		}
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "/modifier/{id}")
	public String modifierArticle(Model model, @PathVariable Long id) {
		if (id != null) {
			Article article = articleRepository.findById(id).get();
			List<Category> categories = categoryRepository.findAll();
			if (categories == null) {
				categories = new ArrayList<Category>();
			}
			model.addAttribute("categories", categories);
			if (article != null) {
				model.addAttribute("article", article);
			}
		}
		return "article/ajouterArticle";
	}
	
	@RequestMapping(value = "/supprimer/{id}")
	public String supprimerArticle(Model model, @PathVariable Long id) {
		if (id != null) {
			Article Article = articleRepository.findById(id).get();
			if (Article != null) {
				//TODO Verification avant suppression
				articleRepository.deleteById(id);
			}
		}
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "/export/") 
	public String exportArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);
		return "article/article";
	}
}
