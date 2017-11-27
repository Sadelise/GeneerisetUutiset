/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geneerisetuutiset.controllers;

import com.geneerisetuutiset.domain.Article;
import com.geneerisetuutiset.domain.Author;
import com.geneerisetuutiset.domain.Category;
import com.geneerisetuutiset.repositories.ArticleRepository;
import com.geneerisetuutiset.repositories.AuthorRepository;
import com.geneerisetuutiset.repositories.CategoryRepository;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("news", this.articleRepository.findAll());
        return "index";
    }

    @GetMapping("/news/{id}")
    public String getArticle(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));
        return "article";
    }

    @DeleteMapping("/news/{id}")
    public String deleteArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.getOne(id);
        if (article != null) {
            articleRepository.delete(article);
        }
        return "redirect:/";
    }

    @PostMapping("/news/new")
    public String postArticle(Model model,
            String title, String ingress, String authorName, String content, String categoryName,
            @Param("file") MultipartFile picture) throws IOException {
        Article article = new Article();
        if (picture != null && picture.getContentType().equals("image/picture")) {
            article.setPicture(picture.getBytes());
        }
//        Author author = authorRepository.findByName(authorName);
//        Category category = categoryRepository.findByName(categoryName);
//        article.addAuthor(author);
//        article.addCategory(category);
        article.setContent(content);
        article.setIngress(ingress);
        article.setPublished(LocalDate.now());
        article.setTitle(title);

        articleRepository.save(article);
        return "redirect:/";
    }
}
