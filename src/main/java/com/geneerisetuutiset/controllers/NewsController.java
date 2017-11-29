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
import com.geneerisetuutiset.services.NewsEditingService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsEditingService newsEditingService;

    @RequestMapping("/")
    public String home(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "published");
        model.addAttribute("news", this.articleRepository.findAll(pageable));
        model.addAttribute("categories", this.categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/news/{id}")
    public String getArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.getOne(id);
        article.setTimesRead(article.getTimesRead() + 1);
        model.addAttribute("article", article);
        model.addAttribute("authors", article.getAuthors());
        Pageable pageablePub = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "published");
        model.addAttribute("published", articleRepository.findAll(pageablePub));
        Pageable pageableRead = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "timesRead");
        model.addAttribute("timesRead", articleRepository.findAll(pageableRead));
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
            String title, String ingress, String authorNames, String content, String[] categoryNames,
            @Param("file") MultipartFile picture, RedirectAttributes redirectAttributes) throws IOException {
        newsEditingService.addNew(title, ingress, authorNames, content, categoryNames, picture);
        if (title == null || authorNames == null || content == null) {
            redirectAttributes.addFlashAttribute("message", "Artikkelin lisäys epäonnistui. Artikkelilla täytyy olla ainakin otsikko, yksi kirjoittaja ja sisältöä.");
            return "redirect:/control";
        }
        return "redirect:/";
    }

    @GetMapping("/control")
    public String controlPanel(Model model) {
        model.addAttribute("news", this.articleRepository.findAll());
        model.addAttribute("categories", this.categoryRepository.findAll());
        return "control";
    }

    @GetMapping(path = "/pictures/{id}", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return articleRepository.getOne(id).getPicture();
    }

    @GetMapping("/news/category/{name}")
    public String getArticlesByCategory(Model model, @PathVariable String name) {
        Category category = categoryRepository.getOneByName(name);
        model.addAttribute("news", category.getArticles());
        return "filtered";
    }

    @GetMapping("/news/filtered/published")
    public String getArticlesByPublishingTime(Model model) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "published");
        model.addAttribute("published", articleRepository.findAll(pageable));
        return "filtered";
    }

    @GetMapping("/news/filtered/timesRead")
    public String getArticlesByPopularity(Model model) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "timesRead");
        model.addAttribute("timesRead", articleRepository.findAll(pageable));
        return "filtered";
    }
}
